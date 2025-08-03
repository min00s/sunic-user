package com.sunic.user.aggregate.userworkspace.logic;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sunic.user.aggregate.userworkspace.store.UserWorkspaceStore;
import com.sunic.user.spec.userworkspace.entity.UserWorkspace;
import com.sunic.user.spec.userworkspace.exception.WorkspaceNotFoundException;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceCdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceRdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceUdo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserWorkspaceLogic {
	private final UserWorkspaceStore userWorkspaceStore;

	@Transactional
	public void registerUserWorkspace(UserWorkspaceCdo userWorkspaceCdo) {
		userWorkspaceStore.save(UserWorkspace.create(userWorkspaceCdo));
	}

	public UserWorkspaceRdo retrieveUserWorkspace(Integer id) {
		UserWorkspace workspace = userWorkspaceStore.findById(id)
			.orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found with id: " + id));

		return workspace.toRdo();
	}

	@Transactional
	public String modifyUserWorkspace(UserWorkspaceUdo userWorkspaceModifySdo) {
		UserWorkspace workspace = userWorkspaceStore.findById(userWorkspaceModifySdo.getId())
			.orElseThrow(
				() -> new WorkspaceNotFoundException("Workspace not found with id: " + userWorkspaceModifySdo.getId()));

		workspace.modify(userWorkspaceModifySdo);

		userWorkspaceStore.save(workspace);
		return "Workspace modified successfully";
	}

	@Transactional
	public void deleteUserWorkspace(Integer id) {
		UserWorkspace workspace = userWorkspaceStore.findById(id)
			.orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found with id: " + id));
		workspace.deleteState(workspace.getModifier());
		userWorkspaceStore.save(workspace);
	}
}