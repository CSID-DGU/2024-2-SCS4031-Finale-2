package com.finale.user.dto;

import com.finale.user.entity.StudentArtist;

public record StudentArtistDto(
	Long id,
	String schoolEmail,
	String schoolName,
	String major
) {
	public static StudentArtistDto from(StudentArtist studentArtist) {
		return new StudentArtistDto(
			studentArtist.getId(),
			studentArtist.getSchoolEmail(),
			studentArtist.getSchoolName(),
			studentArtist.getMajor()
		);
	}
}