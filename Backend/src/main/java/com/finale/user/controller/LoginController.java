package com.finale.user.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.finale.global.jwt.JwtProvider;
import com.finale.global.jwt.JwtToken;
import com.finale.global.jwt.JwtUser;
import com.finale.global.security.UserDetailService;
import com.finale.product.entity.HashTag;
import com.finale.user.dto.KakaoOAuth2Response;
import com.finale.user.entity.User;
import com.finale.user.entity.UserInfo;
import com.finale.user.repository.UserRepository;
import com.finale.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//Todo: Swagger 추가
public class LoginController {
	private final UserDetailService userDetailsService;
	private final UserService userService;
	private final JwtProvider jwtProvider;

	// 임시 회원가입 url. 유저를 생성하고 jwt 토큰을 반환한다.
	@GetMapping("/test/signup")
	public JwtToken signup() {

		return jwtProvider.createToken(JwtUser.of(userService.getUserInfo(1L).id()));
	}

	/*
	http://1.618.s3-website.ap-northeast-2.amazonaws.com?accessToken="asfas"&refreshToken="asdfasdf"
또는
http://1.618.s3-website.ap-northeast-2.amazonaws.com/signup?accessToken="asfas"&refreshToken="asdfasdf"
로 리다이렉트
	 */
	@GetMapping("/oauth2/login/kakao")
	public RedirectView loginKakao(@AuthenticationPrincipal OAuth2User oAuth2User) {
		KakaoOAuth2Response kakaoUser = KakaoOAuth2Response.from(oAuth2User.getAttributes());
		JwtToken jwtToken = jwtProvider.createToken(userDetailsService.loadUserByEmail(kakaoUser.email(), kakaoUser.nickname()));
		boolean isSignup = userService.checkSignup(kakaoUser.email());

		String targetUrl;
		if (isSignup) {
			targetUrl = "http://1.618.s3-website.ap-northeast-2.amazonaws.com?accessToken=" + jwtToken.getAccessToken() + "&refreshToken=" + jwtToken.getRefreshToken();
		} else {
			targetUrl = "http://1.618.s3-website.ap-northeast-2.amazonaws.com/signup?accessToken=" + jwtToken.getAccessToken() + "&refreshToken=" + jwtToken.getRefreshToken();
		}

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(targetUrl);
		return redirectView;
	}
}
