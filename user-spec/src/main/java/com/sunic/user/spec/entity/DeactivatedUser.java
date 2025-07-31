package com.sunic.user.spec.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DeactivatedUser {
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String birthYear;
    private Integer gender;
    private List<UserWorkspace> userWorkspaces;
    private Integer loginFailCount;

    public static DeactivatedUser fromUser(User user) {
        return DeactivatedUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .phone(user.getPhone())
                .birthYear(user.getBirthYear())
                .gender(user.getGender())
                .userWorkspaces(user.getUserWorkspaces())
                .loginFailCount(user.getLoginFailCount())
                .build();
    }
}