package com.finale.user.dto.response;
import com.finale.user.dto.StudentArtistDto;
public record StudentArtistRes(
	String schoolEmail,
	String schoolName,
	String major,
	ArtistInfoRes artistInfo
) {
	public static StudentArtistRes from(StudentArtistDto studentArtistDto) {
		return new StudentArtistRes(
			studentArtistDto.schoolEmail(),
			studentArtistDto.schoolName(),
			studentArtistDto.major(),
			ArtistInfoRes.from(studentArtistDto.artistInfo())
			);
	}
}