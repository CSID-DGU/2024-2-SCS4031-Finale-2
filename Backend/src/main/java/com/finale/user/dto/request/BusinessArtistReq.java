package com.finale.user.dto.request;

import com.finale.user.dto.ArtistInfoDto;

public record BusinessArtistReq(
	String businessNumber,
	String openDate,
	String headName,
	String about
) {

}