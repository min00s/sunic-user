package com.sunic.user.aggregate.user.store.jpo;

import com.sunic.user.spec.user.entity.DeactivatedUserProfile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deactivated_user_profile")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class DeactivatedUserProfileJpo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer userId;

	@Column(length = 50)
	private String nickName;

	@Column(length = 100)
	private String univName;

	private Integer univYear;

	private Integer univSemester;

	@Column(length = 50)
	private String majorCategory;

	@Column(length = 100)
	private String majorName;

	@Column(length = 500)
	private String profileImgUrl;

	public static DeactivatedUserProfileJpo from(DeactivatedUserProfile deactivatedUserProfile) {
		return DeactivatedUserProfileJpo.builder()
			.id(deactivatedUserProfile.getId())
			.userId(deactivatedUserProfile.getUserId())
			.nickName(deactivatedUserProfile.getNickName())
			.univName(deactivatedUserProfile.getUnivName())
			.univYear(deactivatedUserProfile.getUnivYear())
			.univSemester(deactivatedUserProfile.getUnivSemester())
			.majorCategory(deactivatedUserProfile.getMajorCategory())
			.majorName(deactivatedUserProfile.getMajorName())
			.profileImgUrl(deactivatedUserProfile.getProfileImgUrl())
			.build();
	}

	public DeactivatedUserProfile toEntity() {
		return DeactivatedUserProfile.builder()
			.id(this.id)
			.userId(this.userId)
			.nickName(this.nickName)
			.univName(this.univName)
			.univYear(this.univYear)
			.univSemester(this.univSemester)
			.majorCategory(this.majorCategory)
			.majorName(this.majorName)
			.profileImgUrl(this.profileImgUrl)
			.build();
	}
}