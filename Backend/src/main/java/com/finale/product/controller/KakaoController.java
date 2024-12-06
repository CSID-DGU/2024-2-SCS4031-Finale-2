package com.finale.product.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@RestController
@RequestMapping("/oauth2/code")
public class KakaoController {

    @PostMapping("/kakao")
    public ResponseEntity<?> getAccessToken(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        String clientId = "<YOUR_CLIENT_ID>";
        String redirectUri = "<YOUR_REDIRECT_URI>";
        String clientSecret = "<YOUR_CLIENT_SECRET>";

        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);
        if (clientSecret != null) {
            body.add("client_secret", clientSecret);
        }

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, requestEntity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Failed to fetch token");
        }
    }
}
