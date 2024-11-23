package com.finale.user.dto;

import lombok.Builder;

@Builder
public record UserTypeDto(
	String role,
	String userType
) {
}