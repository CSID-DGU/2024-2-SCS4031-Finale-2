package com.finale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.finale.global.jwt.JwtProperties;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.finale")
@EnableConfigurationProperties(JwtProperties.class)
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
