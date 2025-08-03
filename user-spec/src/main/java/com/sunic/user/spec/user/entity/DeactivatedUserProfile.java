package com.sunic.user.spec.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DeactivatedUserProfile {
	private Integer id;
	private Integer userId;
	private String nickName;
	private String univName;
	private Integer univYear;
	private Integer univSemester;
	private String majorCategory;
	private String majorName;
	private String profileImgUrl;

	public static DeactivatedUserProfile fromUserProfile(UserProfile userProfile) {
		return DeactivatedUserProfile.builder()
			.id(userProfile.getId())
			.userId(userProfile.getUserId())
			.nickName(userProfile.getNickName())
			.univName(userProfile.getUnivName())
			.univYear(userProfile.getUnivYear())
			.univSemester(userProfile.getUnivSemester())
			.majorCategory(userProfile.getMajorCategory())
			.majorName(userProfile.getMajorName())
			.profileImgUrl(userProfile.getProfileImgUrl())
			.build();
	}
}