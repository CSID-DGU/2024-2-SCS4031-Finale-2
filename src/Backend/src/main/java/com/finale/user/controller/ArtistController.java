package com.finale.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finale.global.jwt.JwtProvider;
import com.finale.global.jwt.JwtUser;
import com.finale.user.dto.request.BusinessArtistReq;
import com.finale.user.dto.request.StudentArtistReq;
import com.finale.user.dto.response.BusinessArtistRes;
import com.finale.user.dto.response.StudentArtistRes;
import com.finale.user.service.ArtistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ArtistController {
	private final ArtistService artistService;
	private final JwtProvider jwtProvider;
	@PostMapping("/v1/artists/students")
	public ResponseEntity<StudentArtistRes> registerStudents(
		@RequestBody StudentArtistReq studentArtistReq,
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		StudentArtistRes response = StudentArtistRes.from(artistService.registerStudentsArtist(studentArtistReq, jwtUser.getId()));
		return ResponseEntity.ok(response);
	}
	
    @PostMapping("/v1/artists/bussinesses")
	public ResponseEntity<BusinessArtistRes> registerbussinsess(
		@RequestBody BusinessArtistReq businessArtistReq,
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		BusinessArtistRes response = BusinessArtistRes.from(artistService.registerBusinessArtist(businessArtistReq, jwtUser.getId()));
		return ResponseEntity.ok(response);
	}
	
    // 작가 목록 조회(페이징)
	@GetMapping("/v1/artists")
	public String getArtists() {
		return "ok";
	}
	// 작가 프로필 조회
	@GetMapping("/v1/artists/{userId}")
	public String getArtist() {
		return "ok";
	}
}