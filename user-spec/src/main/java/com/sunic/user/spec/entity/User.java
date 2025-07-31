package com.sunic.user.spec.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}