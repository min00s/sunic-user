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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

        User user = User.create(userRegisterSdo, passwordEncoder.encode(userRegisterSdo.getPassword()));

        userStore.save(user);
    }

    public UserLoginRdo loginUser(UserLoginSdo userLoginSdo) {
        User user = userStore.findByEmail(userLoginSdo.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(userLoginSdo.getPassword(), user.getPassword())) {
            user.updateLoginFailCount();
            userStore.save(user);
            throw new InvalidCredentialsException("Invalid email or password");
        }

        user.resetLoginFailCount();
        userStore.save(user);

        return user.toLoginRdo();
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

    public boolean checkUser(int userId) {
        return userStore.findById(userId).isPresent();
    }

    public boolean checkAdminUser(int userId) {
        return userStore.findById(userId)
                .map(User::isAdmin)
                .orElse(false);
    }
}