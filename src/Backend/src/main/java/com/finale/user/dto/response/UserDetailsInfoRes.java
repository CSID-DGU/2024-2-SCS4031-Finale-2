package com.finale.user.dto.response;

import java.util.List;

import com.finale.product.entity.HashTag;
import com.finale.user.dto.UserInfoDto;

import lombok.Builder;

@Builder
public record UserDetailsInfoRes(
	String name,
	String userImageUrl,
	String nickname,
	String email,
	String birthdate,
	String phone,
	String address,
	List<HashTag> hashTags
) {
	public static UserDetailsInfoRes fromDto(UserInfoDto userInfoDto) {
		return UserDetailsInfoRes.builder()
			.name(userInfoDto.name())
			.userImageUrl(userInfoDto.userImageUrl())
			.nickname(userInfoDto.nickname())
			.email(userInfoDto.email())
			.birthdate(userInfoDto.birthdate())
			.phone(userInfoDto.phone())
			.address(userInfoDto.address())
			.hashTags(userInfoDto.hashTags())
			.build();
	}
}