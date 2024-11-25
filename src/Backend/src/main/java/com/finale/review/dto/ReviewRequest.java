package com.finale.review.dto;
import com.finale.product.entity.Product;
import com.finale.review.entity.Review;
import com.finale.user.entity.User;
public record ReviewRequest(Long writerId, String content) {
    public Review toEntity(User writer, Product product) {
        return Review.builder()
                .content(content)
                .writer(writer)
                .product(product)
                .build();
    }
}