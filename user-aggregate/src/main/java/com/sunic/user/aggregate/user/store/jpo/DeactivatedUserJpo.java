package com.sunic.user.aggregate.user.store.jpo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.ColumnDefault;

import com.sunic.user.spec.user.entity.DeactivatedUser;
import com.sunic.user.spec.user.entity.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deactivated_user")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class DeactivatedUserJpo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;
	private String name;
	private String password;
	private String phone;

	@Column(nullable = false)
	@ColumnDefault("'USER'")
	private String roles;

	private String birthYear;
	private Integer gender;

	@ManyToMany
	private List<UserWorkspaceJpo> userWorkspaces;

	private Integer loginFailCount;

	public static DeactivatedUserJpo from(DeactivatedUser deactivatedUser) {
		String rolesString = deactivatedUser.getRoles() != null && !deactivatedUser.getRoles().isEmpty()
			? deactivatedUser.getRoles().stream().map(Role::name).collect(Collectors.joining(","))
			: Role.USER.name();
		
		return DeactivatedUserJpo.builder()
			.email(deactivatedUser.getEmail())
			.name(deactivatedUser.getName())
			.password(deactivatedUser.getPassword())
			.phone(deactivatedUser.getPhone())
			.roles(rolesString)
			.birthYear(deactivatedUser.getBirthYear())
			.gender(deactivatedUser.getGender())
			.loginFailCount(deactivatedUser.getLoginFailCount())
			.userWorkspaces(
				deactivatedUser.getUserWorkspaces() != null ? deactivatedUser.getUserWorkspaces()
					.stream()
					.map(UserWorkspaceJpo::from)
					.collect(Collectors.toList()) : List.of())
			.build();
	}

	public DeactivatedUser toDeactivatedUser() {
		List<Role> roleList = this.roles != null && !this.roles.trim().isEmpty()
			? Arrays.stream(this.roles.split(","))
			.map(String::trim)
			.map(Role::valueOf)
			.collect(Collectors.toList())
			: List.of(Role.USER);

		return DeactivatedUser.builder()
			.id(this.id)
			.email(this.email)
			.name(this.name)
			.password(this.password)
			.phone(this.phone)
			.roles(roleList)
			.birthYear(this.birthYear)
			.gender(this.gender)
			.loginFailCount(this.loginFailCount)
			.build();
	}
}