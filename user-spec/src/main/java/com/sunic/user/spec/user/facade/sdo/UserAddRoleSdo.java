package com.sunic.user.spec.user.facade.sdo;

import com.sunic.user.spec.user.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserAddRoleSdo {
	private Integer userId;
	private Role role;
}