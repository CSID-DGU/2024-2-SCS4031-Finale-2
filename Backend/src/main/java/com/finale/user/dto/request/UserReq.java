package com.finale.user.dto.request;

import java.util.List;

import com.finale.product.entity.HashTag;
import com.finale.user.dto.UserCommonInfoDto;
import com.finale.user.dto.UserDto;
import com.finale.user.dto.UserInfoDto;

public record UserReq(
	String name,
	String email,
	String birthdate,
	String phone,
	String address,
	List<HashTag> hashTags,
	String userImageUrl,
	String nickname
) {
	public UserInfoDto toUserInfoDto() {
		return new UserInfoDto(
			name(),
			email(),
			birthdate(),
			phone(),
			address(),
			hashTags()
		);
	}

	public UserCommonInfoDto toUserCommonInfoDto() {
		return new UserCommonInfoDto(
			nickname(),
			userImageUrl()
		);
	}
}