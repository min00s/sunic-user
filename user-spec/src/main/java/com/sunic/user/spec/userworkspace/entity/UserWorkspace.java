package com.sunic.user.spec.userworkspace.entity;

import org.springframework.beans.BeanUtils;

import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceCdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceRdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceUdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserWorkspace {
	private Integer id;
	private String name;
	private String description;
	private UserWorkspaceType type;
	private UserWorkspaceState state;
	private Long registeredTime;
	private Integer registrant;
	private Long modifiedTime;
	private Integer modifier;

	public static UserWorkspace create(UserWorkspaceCdo userWorkspaceCdo) {
		long currentTime = System.currentTimeMillis();
		return UserWorkspace.builder()
			.name(userWorkspaceCdo.getName())
			.description(userWorkspaceCdo.getDescription())
			.type(userWorkspaceCdo.getType())
			.state(UserWorkspaceState.Active)
			.registeredTime(currentTime)
			.registrant(userWorkspaceCdo.getRegistrant())
			.modifiedTime(currentTime)
			.modifier(userWorkspaceCdo.getRegistrant())
			.build();
	}

	public void modify(UserWorkspaceUdo userWorkspaceUdo) {
		BeanUtils.copyProperties(userWorkspaceUdo, this);
		this.modifiedTime = System.currentTimeMillis();
	}

	public void deleteState(Integer modifier) {
		this.state = UserWorkspaceState.Removed;
		this.modifiedTime = System.currentTimeMillis();
		this.modifier = modifier;
	}

	public UserWorkspaceRdo toRdo() {
		UserWorkspaceRdo rdo = new UserWorkspaceRdo();
		BeanUtils.copyProperties(this, rdo);
		return rdo;
	}
}