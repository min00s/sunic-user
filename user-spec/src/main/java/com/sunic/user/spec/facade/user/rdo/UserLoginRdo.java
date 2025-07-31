package com.sunic.user.spec.facade.user.rdo;

import java.util.List;

import com.sunic.user.spec.facade.userworkspace.rdo.UserWorkspaceRdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserLoginRdo {
    private Integer id;
    private String email;
    private String name;
    private String phone;
    private Integer gender;
    private List<UserWorkspaceRdo> userWorkspaces;
}