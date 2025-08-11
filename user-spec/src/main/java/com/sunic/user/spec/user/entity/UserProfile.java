package com.sunic.user.spec.user.entity;

import com.sunic.user.spec.user.facade.sdo.UserProfileCdo;
import com.sunic.user.spec.user.facade.sdo.UserProfileRdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserProfile {
	private Integer id;
	private Integer userId;
	private String nickName;
	private String univName;
	private Integer univYear;
	private Integer univSemester;
	private String majorCategory;
	private String majorName;
	private String profileImgUrl;
	private String enrollReason;

	public static UserProfile create(Integer userId, UserProfileCdo userProfileCdo) {
		return UserProfile.builder()
			.userId(userId)
			.nickName(userProfileCdo.getNickName())
			.univName(userProfileCdo.getUnivName())
			.univYear(userProfileCdo.getUnivYear())
			.univSemester(userProfileCdo.getUnivSemester())
			.majorCategory(userProfileCdo.getMajorCategory())
			.majorName(userProfileCdo.getMajorName())
			.profileImgUrl(userProfileCdo.getProfileImgUrl())
			.enrollReason(userProfileCdo.getEnrollReason())
			.build();
	}

	public UserProfile modify(String nickName, String univName, Integer univYear, Integer univSemester,
		String majorCategory, String majorName, String profileImgUrl, String enrollReason) {
		return UserProfile.builder()
			.id(this.id)
			.userId(this.userId)
			.nickName(nickName)
			.univName(univName)
			.univYear(univYear)
			.univSemester(univSemester)
			.majorCategory(majorCategory)
			.majorName(majorName)
			.profileImgUrl(profileImgUrl)
			.enrollReason(enrollReason)
			.build();
	}

	public UserProfileRdo toRdo() {
		return UserProfileRdo.builder()
			.id(id)
			.nickName(nickName)
			.univName(univName)
			.univYear(univYear)
			.univSemester(univSemester)
			.majorCategory(majorCategory)
			.majorName(majorName)
			.profileImgUrl(profileImgUrl)
			.enrollReason(enrollReason)
			.build();
	}
}
