package com.sunic.user.aggregate.user.store.jpo;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.BeanUtils;

import com.sunic.user.spec.user.entity.DeactivatedUser;
import com.sunic.user.spec.user.entity.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@ColumnDefault("'USER'")
	private Role role;

	private String birthYear;
	private Integer gender;

	@ManyToMany
	private List<UserWorkspaceJpo> userWorkspaces;

	private Integer loginFailCount;

	public static DeactivatedUserJpo from(DeactivatedUser deactivatedUser) {
		return DeactivatedUserJpo.builder()
			.email(deactivatedUser.getEmail())
			.name(deactivatedUser.getName())
			.password(deactivatedUser.getPassword())
			.phone(deactivatedUser.getPhone())
			.role(deactivatedUser.getRole())
			.birthYear(deactivatedUser.getBirthYear())
			.gender(deactivatedUser.getGender())
			.loginFailCount(deactivatedUser.getLoginFailCount())
			.userWorkspaces(
				deactivatedUser.getUserWorkspaces().stream().map(UserWorkspaceJpo::from).collect(Collectors.toList()))
			.build();
	}

	public DeactivatedUser toDeactivatedUser() {
		DeactivatedUser deactivatedUser = new DeactivatedUser();
		BeanUtils.copyProperties(this, deactivatedUser);
		return deactivatedUser;
	}
}