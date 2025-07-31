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
}