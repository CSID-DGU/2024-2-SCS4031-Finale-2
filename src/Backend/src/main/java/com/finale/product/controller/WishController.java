package com.finale.product.controller;

import com.finale.global.ApiResponse.ApiResponse;
import com.finale.global.ApiResponse.SuccessCode;
import com.finale.global.jwt.JwtUser;
import com.finale.product.dto.ProductPage.Paging;
import com.finale.product.service.ProductLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/wishes")
@RequiredArgsConstructor
public class WishController {
    private ProductLikeService productLikeService;

    @GetMapping
    public ResponseEntity<ApiResponse<Paging>> getAllMyLikeProducts(
            @AuthenticationPrincipal JwtUser jwtUser, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.OK,productLikeService.getLikeProducts(jwtUser.getId(),pageable)));
    }
}
