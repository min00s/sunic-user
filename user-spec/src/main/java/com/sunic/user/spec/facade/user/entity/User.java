package com.sunic.user.spec.facade.user.entity;

import com.sunic.user.spec.facade.userworkspace.entity.UserWorkspace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    public User updateLoginFailCount() {
        return User.builder()
                .id(this.id)
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .phone(this.phone)
                .birthYear(this.birthYear)
                .gender(this.gender)
                .userWorkspaces(this.userWorkspaces)
                .loginFailCount(this.loginFailCount + 1)
                .lastLoginTime(this.lastLoginTime)
                .lastLoginFailTime(LocalDateTime.now())
                .build();
    }

    public User resetLoginFailCount() {
        return User.builder()
                .id(this.id)
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .phone(this.phone)
                .birthYear(this.birthYear)
                .gender(this.gender)
                .userWorkspaces(this.userWorkspaces)
                .loginFailCount(0)
                .lastLoginTime(LocalDateTime.now())
                .lastLoginFailTime(this.lastLoginFailTime)
                .build();
    }

    public static User create(String email, String name, String password, String phone, 
                             String birthYear, Integer gender) {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .phone(phone)
                .birthYear(birthYear)
                .gender(gender)
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
}