package com.sunic.user.aggregate.userworkspace.logic;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sunic.user.aggregate.userworkspace.store.UserWorkspaceStore;
import com.sunic.user.spec.entity.UserWorkspace;
import com.sunic.user.spec.entity.UserWorkspaceState;
import com.sunic.user.spec.exception.WorkspaceAlreadyExistsException;
import com.sunic.user.spec.exception.WorkspaceNotFoundException;
import com.sunic.user.spec.facade.userworkspace.UserWorkspaceFacade;
import com.sunic.user.spec.facade.userworkspace.rdo.UserWorkspaceRdo;
import com.sunic.user.spec.facade.userworkspace.sdo.UserWorkspaceModifySdo;
import com.sunic.user.spec.facade.userworkspace.sdo.UserWorkspaceRegisterSdo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserWorkspaceLogic implements UserWorkspaceFacade {
    private final UserWorkspaceStore userWorkspaceStore;

    @Override
    @Transactional
    public Integer registerUserWorkspace(UserWorkspaceRegisterSdo userWorkspaceRegisterSdo) {
        if (userWorkspaceStore.existsByName(userWorkspaceRegisterSdo.getName())) {
            throw new WorkspaceAlreadyExistsException("Workspace with name already exists: " + userWorkspaceRegisterSdo.getName());
        }

        UserWorkspace workspace = UserWorkspace.create(
                userWorkspaceRegisterSdo.getName(),
                userWorkspaceRegisterSdo.getDescription(),
                userWorkspaceRegisterSdo.getType(),
                userWorkspaceRegisterSdo.getRegistrant());

        UserWorkspace savedWorkspace = userWorkspaceStore.save(workspace);
        return savedWorkspace.getId();
    }

    @Override
    public UserWorkspaceRdo retrieveUserWorkspace(Integer id) {
        UserWorkspace workspace = userWorkspaceStore.findById(id)
                .orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found with id: " + id));

        return UserWorkspaceRdo.builder()
                .id(workspace.getId())
                .name(workspace.getName())
                .description(workspace.getDescription())
                .state(workspace.getState())
                .type(workspace.getType())
                .registeredTime(workspace.getRegisteredTime())
                .registrant(workspace.getRegistrant())
                .modifiedTime(workspace.getModifiedTime())
                .modifier(workspace.getModifier())
                .build();
    }

    @Override
    @Transactional
    public String modifyUserWorkspace(UserWorkspaceModifySdo userWorkspaceModifySdo) {
        UserWorkspace workspace = userWorkspaceStore.findById(userWorkspaceModifySdo.getId())
                .orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found with id: " + userWorkspaceModifySdo.getId()));

        String newName = userWorkspaceModifySdo.getName() != null ? userWorkspaceModifySdo.getName() : workspace.getName();
        String newDescription = userWorkspaceModifySdo.getDescription() != null ? userWorkspaceModifySdo.getDescription() : workspace.getDescription();
        Integer modifier = userWorkspaceModifySdo.getModifier() != null ? userWorkspaceModifySdo.getModifier() : workspace.getModifier();
        
        UserWorkspace updatedWorkspace;
        if (userWorkspaceModifySdo.getState() != null && !userWorkspaceModifySdo.getState().equals(workspace.getState())) {
            updatedWorkspace = workspace.changeState(userWorkspaceModifySdo.getState(), modifier);
            if (!newName.equals(workspace.getName()) || !newDescription.equals(workspace.getDescription())) {
                updatedWorkspace = updatedWorkspace.modify(newName, newDescription, modifier);
            }
        } else {
            updatedWorkspace = workspace.modify(newName, newDescription, modifier);
        }

        userWorkspaceStore.save(updatedWorkspace);
        return "Workspace modified successfully";
    }

    @Override
    @Transactional
    public void deleteUserWorkspace(Integer id) {
        UserWorkspace workspace = userWorkspaceStore.findById(id)
                .orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found with id: " + id));

        UserWorkspace deletedWorkspace = workspace.changeState(UserWorkspaceState.Removed, workspace.getModifier());

        userWorkspaceStore.save(deletedWorkspace);
    }
}