package com.finale.user.dto;

import java.util.List;
import java.util.Set;

import com.finale.product.entity.HashTag;
import com.finale.user.entity.UserInfo;

import lombok.Builder;

public record UserInfoDto(
	String name,
	String email,
	String birthdate,
	String phone,
	String address,
	List<HashTag> hashTags
) {
	public static UserInfoDto fromEntity(UserInfo userInfo) {
		return new UserInfoDto(
			userInfo.getName(),
			userInfo.getEmail(),
			userInfo.getBirthdate(),
			userInfo.getPhone(),
			userInfo.getAddress(),
			userInfo.getHashTags()
		);
	}

	public UserInfo toEntity() {
		return new UserInfo(
			name,
			email,
			birthdate,
			phone,
			address,
			hashTags
		);
	}
}