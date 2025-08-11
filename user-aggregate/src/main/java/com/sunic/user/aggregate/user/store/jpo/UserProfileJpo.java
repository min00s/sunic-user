package com.sunic.user.aggregate.user.store.jpo;

import com.sunic.user.spec.user.entity.UserProfile;

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
@Table(name = "user_profile")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class UserProfileJpo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
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

	@Column(length = 500)
	private String enrollReason;

	public static UserProfileJpo from(UserProfile userProfile) {
		return UserProfileJpo.builder()
			.id(userProfile.getId())
			.userId(userProfile.getUserId())
			.nickName(userProfile.getNickName())
			.univName(userProfile.getUnivName())
			.univYear(userProfile.getUnivYear())
			.univSemester(userProfile.getUnivSemester())
			.majorCategory(userProfile.getMajorCategory())
			.majorName(userProfile.getMajorName())
			.profileImgUrl(userProfile.getProfileImgUrl())
			.enrollReason(userProfile.getEnrollReason())
			.build();
	}

	public UserProfile toEntity() {
		return UserProfile.builder()
			.id(this.id)
			.userId(this.userId)
			.nickName(this.nickName)
			.univName(this.univName)
			.univYear(this.univYear)
			.univSemester(this.univSemester)
			.majorCategory(this.majorCategory)
			.majorName(this.majorName)
			.profileImgUrl(this.profileImgUrl)
			.enrollReason(this.enrollReason)
			.build();
	}
}