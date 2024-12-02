package com.finale.e2e;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.finale.global.jwt.JwtProvider;
import com.finale.global.jwt.JwtToken;
import com.finale.global.jwt.JwtUser;
public class JwtTestUtils {
	public static JwtToken generateValidToken(JwtProvider jwtProvider, Long userId, String username, String role) {
		JwtUser jwtUser = JwtUser.builder()
			.id(userId)
			.username(username)
			.authorities(List.of(new SimpleGrantedAuthority("ROLE_" + role)))
			.build();
		return jwtProvider.createToken(jwtUser);
	}
}