package com.sunic.user.aggregate.user.store.jpo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.ColumnDefault;

import com.sunic.user.spec.user.entity.Role;
import com.sunic.user.spec.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@ColumnDefault("'USER'")
	private Role role;

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "user_role")
	private List<UserWorkspaceJpo> userWorkspaces = new ArrayList<>();

	@ColumnDefault("0")
	private Integer loginFailCount;

	private LocalDateTime lastLoginTime;
	private LocalDateTime lastLoginFailTime;

	public static UserJpo from(User user) {
		return UserJpo.builder()
			.id(user.getId())
			.email(user.getEmail())
			.name(user.getName())
			.password(user.getPassword())
			.phone(user.getPhone())
			.birthYear(user.getBirthYear())
			.gender(user.getGender())
			.role(user.getRole())
			.loginFailCount(user.getLoginFailCount())
			.lastLoginTime(user.getLastLoginTime())
			.lastLoginFailTime(user.getLastLoginFailTime())
			.userWorkspaces(user.getUserWorkspaces().stream().map(UserWorkspaceJpo::from).collect(Collectors.toList()))
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
		return User.builder()
			.id(this.id)
			.email(this.email)
			.name(this.name)
			.password(this.password)
			.phone(this.phone)
			.birthYear(this.birthYear)
			.gender(this.gender)
			.role(this.role)
			.loginFailCount(this.loginFailCount)
			.lastLoginTime(this.lastLoginTime)
			.lastLoginFailTime(this.lastLoginFailTime)
			.build();
	}
}