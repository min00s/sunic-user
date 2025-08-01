package com.sunic.user.spec.user.entity;

import com.sunic.user.spec.user.facade.sdo.UserLoginRdo;
import com.sunic.user.spec.user.facade.sdo.UserRegisterSdo;
import com.sunic.user.spec.userworkspace.entity.UserWorkspace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String birthYear;
    private Integer gender;
    private List<UserWorkspace> userWorkspaces;
    private Integer loginFailCount;
    private LocalDateTime lastLoginTime;
    private LocalDateTime lastLoginFailTime;

    public void updateLoginFailCount() {
        this.loginFailCount++;
        this.lastLoginFailTime = LocalDateTime.now();
    }

    public void resetLoginFailCount() {
        this.loginFailCount = 0;
        this.lastLoginTime = LocalDateTime.now();
    }

    public static User create(UserRegisterSdo userRegisterSdo, String password) {
        return User.builder()
                .email(userRegisterSdo.getEmail())
                .name(userRegisterSdo.getName())
                .password(password)
                .phone(userRegisterSdo.getPhone())
                .birthYear(userRegisterSdo.getBirthYear())
                .gender(userRegisterSdo.getGender())
                .loginFailCount(0)
                .build();
    }

    public User modify(String name, String phone, String birthYear, Integer gender) {
        return User.builder()
                .id(this.id)
                .email(this.email)
                .name(name)
                .password(this.password)
                .phone(phone)
                .birthYear(birthYear)
                .gender(gender)
                .userWorkspaces(this.userWorkspaces)
                .loginFailCount(this.loginFailCount)
                .lastLoginTime(this.lastLoginTime)
                .lastLoginFailTime(this.lastLoginFailTime)
                .build();
    }

    public User changePassword(String newPassword) {
        return User.builder()
                .id(this.id)
                .email(this.email)
                .name(this.name)
                .password(newPassword)
                .phone(this.phone)
                .birthYear(this.birthYear)
                .gender(this.gender)
                .userWorkspaces(this.userWorkspaces)
                .loginFailCount(this.loginFailCount)
                .lastLoginTime(this.lastLoginTime)
                .lastLoginFailTime(this.lastLoginFailTime)
                .build();
    }

    public UserLoginRdo toLoginRdo() {
        return UserLoginRdo.builder()
                .id(this.id)
                .email(this.email)
                .name(this.name)
                .phone(this.phone)
                .gender(this.gender)
                .userWorkspaces(this.userWorkspaces.stream().map(UserWorkspace::toRdo).collect(Collectors.toList()))
                .build();
    }
}