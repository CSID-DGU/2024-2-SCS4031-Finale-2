package com.finale.user.dto;

public record UserFollowingDto(
	Long userId,
	String imageUrl,
	String nickname,
	Long totalFollowers,
	Long totalLikes
) {
}