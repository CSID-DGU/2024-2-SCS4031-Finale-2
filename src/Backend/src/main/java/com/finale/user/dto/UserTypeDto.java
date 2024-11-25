package com.finale.user.dto;

import com.finale.user.entity.ArtistType;

import lombok.Builder;

@Builder
public record UserTypeDto(
	String role,
	ArtistType userType
) {
}