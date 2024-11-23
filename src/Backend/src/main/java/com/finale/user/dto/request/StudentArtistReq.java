package com.finale.user.dto.request;

import com.finale.user.dto.StudentArtistDto;

public record StudentArtistReq(
	String schoolEmail,
	String schoolName,
	String major,
	String about
) {
}