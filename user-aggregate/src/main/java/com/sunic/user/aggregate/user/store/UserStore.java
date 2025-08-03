package com.sunic.user.aggregate.user.store;

import com.sunic.user.aggregate.user.store.jpo.DeactivatedUserJpo;
import com.sunic.user.aggregate.user.store.jpo.UserJpo;
import com.sunic.user.aggregate.user.store.jpo.UserWorkspaceJpo;
import com.sunic.user.aggregate.user.store.repository.DeactivatedUserRepository;
import com.sunic.user.aggregate.user.store.repository.UserRepository;
import com.sunic.user.aggregate.user.store.repository.UserWorkspaceRepository;
import com.sunic.user.spec.user.entity.DeactivatedUser;
import com.sunic.user.spec.user.entity.User;
import com.sunic.user.spec.user.exception.UserNotFoundException;
import com.sunic.user.spec.userworkspace.entity.UserWorkspace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserStore {
    private final UserRepository userRepository;
    private final DeactivatedUserRepository deactivatedUserRepository;
    private final UserWorkspaceRepository userWorkspaceRepository;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void save(User user) {
        userRepository.save(UserJpo.from(user));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserJpo::toEntity);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id)
                .map(UserJpo::toEntity);
    }

    public List<User> findUsersInactiveForMoreThanOneYear(LocalDateTime oneYearAgo) {
        return userRepository.findUsersInactiveForMoreThanOneYear(oneYearAgo)
                .stream()
                .map(UserJpo::toEntity)
                .collect(Collectors.toList());
    }

    public DeactivatedUser findDeactivatedUserByEmail(String email) {
        return deactivatedUserRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Invalid User Name"))
            .toDeactivatedUser();
    }

    public void saveDeactivatedUser(DeactivatedUser deactivatedUser) {
        deactivatedUserRepository.save(DeactivatedUserJpo.from(deactivatedUser));
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }

    public void deleteDeactivatedUser(DeactivatedUser deactivatedUser) {
        deactivatedUserRepository.deleteById(deactivatedUser.getId());
    }

    public Optional<UserWorkspace> findWorkspaceById(Integer workspaceId) {
        return userWorkspaceRepository.findById(workspaceId)
                .map(UserWorkspaceJpo::toEntity);
    }

    public boolean workspaceExistsByName(String name) {
        return userWorkspaceRepository.existsByName(name);
    }

    public UserWorkspace saveWorkspace(UserWorkspace workspace) {
        UserWorkspaceJpo savedJpo = userWorkspaceRepository.save(UserWorkspaceJpo.from(workspace));
        return savedJpo.toEntity();
    }

    public Optional<UserWorkspace> findWorkspaceByName(String name) {
        return userWorkspaceRepository.findByName(name)
                .map(UserWorkspaceJpo::toEntity);
    }
}