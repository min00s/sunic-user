package com.sunic.user.spec.userworkspace.facade.sdo;

import com.sunic.user.spec.userworkspace.entity.UserWorkspaceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserWorkspaceCdo {
	private String name;
	private String description;
	private UserWorkspaceType type;
	private Integer registrant;
}