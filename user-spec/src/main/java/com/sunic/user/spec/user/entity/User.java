package com.sunic.user.spec.user.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.sunic.user.spec.user.facade.sdo.UserLoginRdo;
import com.sunic.user.spec.user.facade.sdo.UserProfileRdo;
import com.sunic.user.spec.user.facade.sdo.UserRegisterSdo;
import com.sunic.user.spec.userworkspace.entity.UserWorkspace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
	private Integer id;
	private String email;
	private String name;
	private String password;
	private String phone;
	private String birthYear;
	private Integer gender;
	private Role role;
	private List<UserWorkspace> userWorkspaces;
	private Integer loginFailCount;
	private LocalDateTime lastLoginTime;
	private LocalDateTime lastLoginFailTime;
	private UserProfile userProfile;

	public void updateLoginFailCount() {
		this.loginFailCount++;
		this.lastLoginFailTime = LocalDateTime.now();
	}

	public void resetLoginFailCount() {
		this.loginFailCount = 0;
		this.lastLoginTime = LocalDateTime.now();
	}

	public static User create(UserRegisterSdo userRegisterSdo, String password) {
		return User.builder()
			.email(userRegisterSdo.getEmail())
			.name(userRegisterSdo.getName())
			.password(password)
			.phone(userRegisterSdo.getPhone())
			.birthYear(userRegisterSdo.getBirthYear())
			.gender(userRegisterSdo.getGender())
			.role(userRegisterSdo.getRole() != null ? userRegisterSdo.getRole() : Role.USER)
			.loginFailCount(0)
			.build();
	}

	public static User fromDeactivateUser(DeactivatedUser deactivatedUser, UserProfile userProfile) {
		return User.builder()
			.id(deactivatedUser.getId())
			.email(deactivatedUser.getEmail())
			.name(deactivatedUser.getName())
			.password(deactivatedUser.getPassword())
			.phone(deactivatedUser.getPhone())
			.role(deactivatedUser.getRole())
			.birthYear(deactivatedUser.getBirthYear())
			.gender(deactivatedUser.getGender())
			.loginFailCount(0)
			.userWorkspaces(deactivatedUser.getUserWorkspaces())
			.userProfile(userProfile)
			.build();
	}

	public User modify(String name, String phone, String birthYear, Integer gender) {
		return User.builder()
			.id(this.id)
			.email(this.email)
			.name(name)
			.password(this.password)
			.phone(phone)
			.birthYear(birthYear)
			.gender(gender)
			.role(this.role)
			.userWorkspaces(this.userWorkspaces)
			.loginFailCount(this.loginFailCount)
			.lastLoginTime(this.lastLoginTime)
			.lastLoginFailTime(this.lastLoginFailTime)
			.userProfile(this.userProfile)
			.build();
	}

	public User changePassword(String newPassword) {
		return User.builder()
			.id(this.id)
			.email(this.email)
			.name(this.name)
			.password(newPassword)
			.phone(this.phone)
			.birthYear(this.birthYear)
			.gender(this.gender)
			.role(this.role)
			.userWorkspaces(this.userWorkspaces)
			.loginFailCount(this.loginFailCount)
			.lastLoginTime(this.lastLoginTime)
			.lastLoginFailTime(this.lastLoginFailTime)
			.userProfile(this.userProfile)
			.build();
	}

	public UserLoginRdo toLoginRdo() {
		UserProfileRdo userProfileRdo = this.userProfile != null ?
			UserProfileRdo.builder()
				.id(this.userProfile.getId())
				.nickName(this.userProfile.getNickName())
				.univName(this.userProfile.getUnivName())
				.univYear(this.userProfile.getUnivYear())
				.univSemester(this.userProfile.getUnivSemester())
				.majorCategory(this.userProfile.getMajorCategory())
				.majorName(this.userProfile.getMajorName())
				.profileImgUrl(this.userProfile.getProfileImgUrl())
				.build() : null;
		
		return UserLoginRdo.builder()
			.id(this.id)
			.email(this.email)
			.name(this.name)
			.phone(this.phone)
			.gender(this.gender)
			.role(this.role)
			.userWorkspaces(this.userWorkspaces.stream().map(UserWorkspace::toRdo).collect(Collectors.toList()))
			.userProfile(userProfileRdo)
			.build();
	}

	public User withUserProfile(UserProfile userProfile) {
		return User.builder()
			.id(this.id)
			.email(this.email)
			.name(this.name)
			.password(this.password)
			.phone(this.phone)
			.birthYear(this.birthYear)
			.gender(this.gender)
			.role(this.role)
			.userWorkspaces(this.userWorkspaces)
			.loginFailCount(this.loginFailCount)
			.lastLoginTime(this.lastLoginTime)
			.lastLoginFailTime(this.lastLoginFailTime)
			.userProfile(userProfile)
			.build();
	}

	public boolean isAdmin() {
		return this.role == Role.ADMIN;
	}
}