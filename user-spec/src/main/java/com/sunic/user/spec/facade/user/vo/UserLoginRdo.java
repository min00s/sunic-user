package com.sunic.user.spec.facade.user.vo;

import com.sunic.user.spec.facade.userworkspace.vo.UserWorkspaceRdo;
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
    private List<UserWorkspaceRdo> userWorkspaces;
}