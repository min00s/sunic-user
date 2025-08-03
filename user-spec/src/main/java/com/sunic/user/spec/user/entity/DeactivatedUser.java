package com.sunic.user.spec.user.entity;

import java.util.List;

import com.sunic.user.spec.userworkspace.entity.UserWorkspace;

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
	private DeactivationReason deactivationReason;
	private String email;
	private String name;
	private String password;
	private List<Role> roles;
	private String phone;
	private String birthYear;
	private Integer gender;
	private List<UserWorkspace> userWorkspaces;
	private Integer loginFailCount;

	public static DeactivatedUser fromUser(User user, DeactivationReason deactivationReason) {
		return DeactivatedUser.builder()
			.id(user.getId())
			.deactivationReason(deactivationReason)
			.email(user.getEmail())
			.name(user.getName())
			.password(user.getPassword())
			.roles(user.getRoles())
			.phone(user.getPhone())
			.birthYear(user.getBirthYear())
			.gender(user.getGender())
			.userWorkspaces(user.getUserWorkspaces())
			.loginFailCount(user.getLoginFailCount())
			.build();
	}
}