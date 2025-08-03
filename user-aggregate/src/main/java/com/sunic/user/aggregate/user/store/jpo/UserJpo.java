package com.sunic.user.aggregate.user.store.jpo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.ColumnDefault;

import com.sunic.user.spec.user.entity.Role;
import com.sunic.user.spec.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class UserJpo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 30, nullable = false, unique = true)
	private String email;

	@Column(length = 30, nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	private String phone;

	private String birthYear;

	private Integer gender;

	@Column(nullable = false)
	@ColumnDefault("'USER'")
	private String roles;

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "user_role")
	private List<UserWorkspaceJpo> userWorkspaces = new ArrayList<>();

	@ColumnDefault("0")
	private Integer loginFailCount;

	private LocalDateTime lastLoginTime;
	private LocalDateTime lastLoginFailTime;

	public static UserJpo from(User user) {
		String rolesString = user.getRoles() != null && !user.getRoles().isEmpty()
			? user.getRoles().stream().map(Role::name).collect(Collectors.joining(","))
			: Role.USER.name();
		
		return UserJpo.builder()
			.id(user.getId())
			.email(user.getEmail())
			.name(user.getName())
			.password(user.getPassword())
			.phone(user.getPhone())
			.birthYear(user.getBirthYear())
			.gender(user.getGender())
			.roles(rolesString)
			.loginFailCount(user.getLoginFailCount())
			.lastLoginTime(user.getLastLoginTime())
			.lastLoginFailTime(user.getLastLoginFailTime())
			.userWorkspaces(user.getUserWorkspaces() != null ?
				user.getUserWorkspaces().stream().map(UserWorkspaceJpo::from).collect(Collectors.toList()) :
				new ArrayList<>())
			.build();
	}

	public void updateLoginFailCount() {
		this.loginFailCount = this.loginFailCount + 1;
		this.lastLoginFailTime = LocalDateTime.now();
	}

	public void resetLoginFailCount() {
		this.loginFailCount = 0;
		this.lastLoginTime = LocalDateTime.now();
	}

	public User toEntity() {
		List<Role> roleList = this.roles != null && !this.roles.trim().isEmpty()
			? Arrays.stream(this.roles.split(","))
			.map(String::trim)
			.map(Role::valueOf)
			.collect(Collectors.toList())
			: List.of(Role.USER);
		
		return User.builder()
			.id(this.id)
			.email(this.email)
			.name(this.name)
			.password(this.password)
			.phone(this.phone)
			.birthYear(this.birthYear)
			.gender(this.gender)
			.roles(roleList)
			.loginFailCount(this.loginFailCount)
			.lastLoginTime(this.lastLoginTime)
			.lastLoginFailTime(this.lastLoginFailTime)
			.build();
	}
}