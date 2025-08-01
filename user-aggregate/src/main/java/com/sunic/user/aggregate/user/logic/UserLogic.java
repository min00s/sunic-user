package com.sunic.user.aggregate.user.logic;

import com.sunic.user.aggregate.user.store.UserStore;
import com.sunic.user.spec.user.entity.DeactivatedUser;
import com.sunic.user.spec.user.entity.User;
import com.sunic.user.spec.user.exception.InvalidCredentialsException;
import com.sunic.user.spec.user.exception.UserAlreadyExistsException;
import com.sunic.user.spec.user.exception.UserNotFoundException;
import com.sunic.user.spec.user.facade.sdo.UserJoinSdo;
import com.sunic.user.spec.user.facade.sdo.UserLoginRdo;
import com.sunic.user.spec.user.facade.sdo.UserLoginSdo;
import com.sunic.user.spec.user.facade.sdo.UserRegisterSdo;
import com.sunic.user.spec.userworkspace.entity.UserWorkspace;
import com.sunic.user.spec.userworkspace.exception.UserWorkspaceAlreadyExistsException;
import com.sunic.user.spec.userworkspace.exception.WorkspaceNotFoundException;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLogic {
    private final UserStore userStore;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserRegisterSdo userRegisterSdo) {
        if (userStore.existsByEmail(userRegisterSdo.getEmail())) {
            throw new UserAlreadyExistsException("User with email already exists: " + userRegisterSdo.getEmail());
        }

        User user = User.create(
                userRegisterSdo.getEmail(),
                userRegisterSdo.getName(),
                passwordEncoder.encode(userRegisterSdo.getPassword()),
                userRegisterSdo.getPhone(),
                userRegisterSdo.getBirthYear(),
                userRegisterSdo.getGender());

        userStore.save(user);
    }

    public UserLoginRdo loginUser(UserLoginSdo userLoginSdo) {
        User user = userStore.findByEmail(userLoginSdo.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(userLoginSdo.getPassword(), user.getPassword())) {
            User updatedUser = user.updateLoginFailCount();
            userStore.save(updatedUser);
            throw new InvalidCredentialsException("Invalid email or password");
        }

        User updatedUser = user.resetLoginFailCount();
        userStore.save(updatedUser);

        return UserLoginRdo.builder()
                .id(updatedUser.getId())
                .email(updatedUser.getEmail())
                .name(updatedUser.getName())
                .phone(updatedUser.getPhone())
                .gender(updatedUser.getGender())
                .userWorkspaces(convertToWorkspaceRdos(updatedUser.getUserWorkspaces()))
                .build();
    }

    @Transactional
    public void deactivateUser() {
        LocalDateTime oneYearAgo = LocalDateTime.now().minusYears(1);
        List<User> inactiveUsers = userStore.findUsersInactiveForMoreThanOneYear(oneYearAgo);

        for (User user : inactiveUsers) {
            DeactivatedUser deactivatedUser = DeactivatedUser.fromUser(user);

            userStore.saveDeactivatedUser(deactivatedUser);
            userStore.deleteUser(user);
        }
    }

    @Transactional
    public void joinWorkspace(UserJoinSdo userJoinSdo) {
        User user = userStore.findById(userJoinSdo.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userJoinSdo.getUserId()));

        UserWorkspace workspace = userStore.findWorkspaceById(userJoinSdo.getWorkspaceId())
                .orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found with id: " + userJoinSdo.getWorkspaceId()));

        if (user.getUserWorkspaces() != null && user.getUserWorkspaces().contains(workspace)) {
            throw new UserWorkspaceAlreadyExistsException("Already joined to this workspace");
        }

        userStore.save(user);
    }

    private List<UserWorkspaceRdo> convertToWorkspaceRdos(List<UserWorkspace> userWorkspaces) {
        if (userWorkspaces == null) {
            return List.of();
        }
        return userWorkspaces.stream()
                .map(UserWorkspace::toRdo)
                .collect(Collectors.toList());
    }
}