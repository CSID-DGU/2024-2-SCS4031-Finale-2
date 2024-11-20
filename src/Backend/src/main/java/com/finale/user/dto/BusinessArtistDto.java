package com.finale.user.dto;
import com.finale.user.entity.BusinessArtist;
public record BusinessArtistDto(
	Long id,
	Long userId,
	String businessNumber,
	String openDate,
	String headName,
	ArtistInfoDto artistInfo
) {
	public static BusinessArtistDto fromEntity(BusinessArtist businessArtist){
		return new BusinessArtistDto(
			businessArtist.getId(),
			businessArtist.getUserId(),
			businessArtist.getBusinessNumber(),
			businessArtist.getOpenDate(),
			businessArtist.getHeadName(),
			ArtistInfoDto.of(
				businessArtist.getArtistInfo().getTotalFollowers(),
				businessArtist.getArtistInfo().getTotalLikes(),
				businessArtist.getArtistInfo().getAbout()
			)
		);
	}
}