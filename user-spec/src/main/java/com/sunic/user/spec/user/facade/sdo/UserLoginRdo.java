package com.sunic.user.spec.user.facade.sdo;

import com.sunic.user.spec.user.entity.Role;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceRdo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Role role;
    private List<UserWorkspaceRdo> userWorkspaces;
}