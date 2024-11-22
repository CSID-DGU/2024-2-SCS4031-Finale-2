package com.finale.user.service;

import org.springframework.stereotype.Service;
import com.finale.global.jwt.JwtUser;
import com.finale.user.dto.UserDto;
import com.finale.user.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	public UserDto getUser(Long userId) {
		return UserDto.fromEntity(userRepository.findById(userId).orElseThrow());
	}
}