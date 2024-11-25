package com.finale.global.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.finale.global.jwt.JwtProperties;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class ProPertyConfig {
}
