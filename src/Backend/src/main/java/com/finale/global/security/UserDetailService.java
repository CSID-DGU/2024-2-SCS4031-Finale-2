package com.finale.global.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finale.global.jwt.JwtUser;
import com.finale.user.entity.User;
import com.finale.user.entity.UserInfo;
import com.finale.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailService {
	private final UserRepository userRepository;


	public JwtUser loadUserByEmail(String email,String nickname) throws UsernameNotFoundException {
		// 만약 유저가 존재하지 않는다면 저장
		User user = userRepository.findByUserInfoEmail(email)
			.orElseGet(() -> {
				User newUser = User.builder()
					.userInfo(UserInfo.builder()
						.email(email)
						.build())
					.nickname(nickname)
					.build();
				return userRepository.save(newUser);
			});
		return JwtUser.of(user.getId());
	}
}
