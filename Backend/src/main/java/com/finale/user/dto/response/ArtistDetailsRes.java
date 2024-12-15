package com.finale.user.dto.response;

import com.finale.user.dto.ArtistInfoDto;
import com.finale.user.dto.BusinessArtistDto;
import com.finale.user.dto.StudentArtistDto;

public record ArtistDetailsRes(
	Long id,
	String nickname,
	String description,
	Long totalFollowers,
	Long totalLikes,
	String about,
	String ImageUrl,
	boolean isFollowed
) {
	public static ArtistDetailsRes from(ArtistInfoDto artistInfoDto, BusinessArtistDto businessArtistDto, boolean isFollowed) {
		return new ArtistDetailsRes(
			artistInfoDto.id(),
			artistInfoDto.nickname(),
			businessArtistDto.headName(),
			artistInfoDto.totalFollowers(),
			artistInfoDto.totalLikes(),
			artistInfoDto.about(),
			artistInfoDto.artistImageUrl(),
			isFollowed
		);
	}

	public static ArtistDetailsRes from(ArtistInfoDto artistInfoDto, StudentArtistDto studentArtistDto, boolean isFollowed) {
		return new ArtistDetailsRes(
			artistInfoDto.id(),
			artistInfoDto.nickname(),
			studentArtistDto.schoolName(),
			artistInfoDto.totalFollowers(),
			artistInfoDto.totalLikes(),
			artistInfoDto.about(),
			artistInfoDto.artistImageUrl(),
			isFollowed
		);
	}
}