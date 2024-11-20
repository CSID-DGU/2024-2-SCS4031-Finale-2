package com.finale.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finale.global.jwt.JwtProvider;
import com.finale.global.jwt.JwtToken;
import com.finale.global.jwt.JwtUser;
import com.finale.product.entity.HashTag;
import com.finale.user.entity.User;
import com.finale.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//Todo: Swagger 추가
public class LoginController {
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	// 임시 회원가입 url. 유저를 생성하고 jwt 토큰을 반환한다.
	@GetMapping("/test/signup")
	public JwtToken signup() {
		User user = User.builder()
			.nickname("test")
			.email("test@test")
			.birthdate("1999-01-01")
			.address("서울시 강남구")
			.phone("010-1234-5678")
			.hashTags(List.of(HashTag.DREAMLIKE, HashTag.VIBRANCE))
			.build();
		userRepository.save(user);
		return jwtProvider.createToken(JwtUser.of(user.getId()));
	}
    
	// 임시 로그인 url. 로그인한 유저의 정보의 일부를 반환한다.
	@GetMapping("/login")
	public String login(@AuthenticationPrincipal JwtUser jwtUser) {
		User user = userRepository.findById(jwtUser.getId()).orElseThrow();
		return user.getEmail();
	}
}