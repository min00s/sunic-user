package com.sunic.user.aggregate.userworkspace.store;

import com.sunic.user.aggregate.user.store.jpo.UserWorkspaceJpo;
import com.sunic.user.aggregate.user.store.repository.UserWorkspaceRepository;
import com.sunic.user.spec.userworkspace.entity.UserWorkspace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserWorkspaceStore {
    private final UserWorkspaceRepository userWorkspaceRepository;

    public boolean existsByName(String name) {
        return userWorkspaceRepository.existsByName(name);
    }

    public void save(UserWorkspace workspace) {
        userWorkspaceRepository.save(UserWorkspaceJpo.from(workspace));
    }

    public Optional<UserWorkspace> findById(Integer id) {
        return userWorkspaceRepository.findById(id)
                .map(UserWorkspaceJpo::toEntity);
    }

    public Optional<UserWorkspace> findByName(String name) {
        return userWorkspaceRepository.findByName(name)
                .map(UserWorkspaceJpo::toEntity);
    }
}