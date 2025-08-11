package com.sunic.user.spec.user.facade.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserProfileUdo {
	private String nickName;
	private String univName;
	private Integer univYear;
	private Integer univSemester;
	private String majorCategory;
	private String majorName;
	private String profileImgUrl;
	private String enrollReason;
}