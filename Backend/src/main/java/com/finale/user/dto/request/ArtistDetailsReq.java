package com.finale.user.dto.request;

public record ArtistDetailsReq(
	String nickname,
	String description,
	Long totalFollowers,
	Long totalLikes,
	String about
) {
}