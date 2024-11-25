package com.finale.review.dto;

import com.finale.product.entity.Product;
import com.finale.review.entity.Review;
import com.finale.user.entity.User;

public record ReviewResponse(Long id, String content,User writer, Product product) {
    public static ReviewResponse fromEntity(Review review) {
        return new ReviewResponse(review.getId(), review.getContent(),review.getWriter(), review.getProduct());
    }
}
