package com.finale.global.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
	@PostConstruct
	public void logProperties() {
	  System.out.println("JWT Secret: " + secret);
	  System.out.println("Access Token Expiry: " + accessTokenExpireTime);
	  System.out.println("Refresh Token Expiry: " + refreshTokenExpireTime);
	}
	private String secret;
	private long accessTokenExpireTime;
	private long refreshTokenExpireTime;
}
