package com.sunic.user.spec.user.facade.sdo;

import java.util.List;

import com.sunic.user.spec.user.entity.Role;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceRdo;

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
	private List<Role> roles;
	private List<UserWorkspaceRdo> userWorkspaces;
}