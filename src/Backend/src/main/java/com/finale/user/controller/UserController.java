package com.finale.user.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finale.global.jwt.JwtUser;
import com.finale.user.controller.apiDocs.UserApiDocs;
import com.finale.user.dto.UserFollowingDto;
import com.finale.user.dto.response.UserCommonInfoRes;
import com.finale.user.dto.request.UserInfoReq;
import com.finale.user.dto.response.UserDetailsInfoRes;
import com.finale.user.dto.UserTypeDto;
import com.finale.user.dto.response.UserFollowingRes;
import com.finale.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class UserController implements UserApiDocs {
	private final UserService userService;


	
	@GetMapping("/v1/users")
	public ResponseEntity<UserCommonInfoRes> getUsers(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return ResponseEntity.ok(UserCommonInfoRes.fromDto(userService.getUserInfo(jwtUser.getId())));
	}

	@GetMapping("/v1/users/details")
	public ResponseEntity<UserDetailsInfoRes> getUserDetails(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return ResponseEntity.ok(UserDetailsInfoRes.fromDto(userService.getUserInfo(jwtUser.getId())));
	}

	// 유저 타입 조회
	@Operation(summary = "유저 타입 조회", description = "로그인한 유저의 타입과 권한을 조회한다.")
	@GetMapping("/v1/users/type")
	public ResponseEntity<UserTypeDto> getUserType(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return ResponseEntity.ok(userService.getUserType(jwtUser.getId()));
	}

	
	@PutMapping("/v1/users")
	public String updateUserInfo(
		@AuthenticationPrincipal JwtUser jwtUser,
		@RequestBody UserInfoReq userInfoReq
	) {
		// UserInfoDto를 통해서 유저 정보를 수정한다.
		userService.updateUserInfo(userInfoReq.toDto(), jwtUser.getId());
		return "ok";
	}

	
	@PostMapping("/v1/users/following/{artistId}")
	public ResponseEntity<String> followArtist(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PathVariable Long artistId
	) {
		userService.followArtist(jwtUser.getId(), artistId);
		return ResponseEntity.ok().build();
	}

	
	@DeleteMapping("/v1/users/following/{artistId}")
	public ResponseEntity<String> unfollowArtist(
		@AuthenticationPrincipal JwtUser jwtUser,
		@PathVariable Long artistId
	) {
		userService.unfollowArtist(jwtUser.getId(), artistId);
		return ResponseEntity.ok().build();
	}

	
	@GetMapping("/v1/users/following")
	public ResponseEntity<Page<UserFollowingRes>> getFollowingList(
		@AuthenticationPrincipal JwtUser jwtUser,
			@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		return ResponseEntity.ok(userService.getFollowingWithPaging(jwtUser.getId(), pageable));
	}

	
	@DeleteMapping("/v1/users")
	public String deleteUser(
		@AuthenticationPrincipal JwtUser jwtUser
	) {
		return "ok";
	}
}