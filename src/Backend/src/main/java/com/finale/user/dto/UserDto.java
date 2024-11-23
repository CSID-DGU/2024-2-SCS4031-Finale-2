package com.finale.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.finale.product.entity.HashTag;
import com.finale.user.entity.User;

public record UserDto(
	Long id,
	UserInfoDto userInfo,
	String userImageUrl,
	String nickname,
	LocalDateTime createdAt
) {
	public static UserDto fromEntity(User user) {
		return new UserDto(
			user.getId(),
			UserInfoDto.fromEntity(user.getUserInfo()),
			user.getUserImageUrl(),
			user.getNickname(),
			user.getCreatedAt()
		);
	}
}