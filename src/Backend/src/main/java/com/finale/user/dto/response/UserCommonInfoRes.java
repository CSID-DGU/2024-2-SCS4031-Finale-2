package com.finale.user.dto.response;

import java.util.List;

import com.finale.product.entity.HashTag;
import com.finale.user.dto.UserDto;
import com.finale.user.dto.UserInfoDto;

public record UserCommonInfoRes(
	String username,
	List<HashTag> hashTags,
	String userImageUrl
) {
	public static UserCommonInfoRes fromDto(UserDto userDto) {
		return new UserCommonInfoRes(
			userDto.userInfo().name(),
			userDto.userInfo().hashTags(),
			userDto.userImageUrl()
		);
	}
    
}