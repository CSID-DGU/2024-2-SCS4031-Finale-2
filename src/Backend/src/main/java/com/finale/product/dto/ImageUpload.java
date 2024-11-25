package com.finale.product.dto;

import com.finale.product.entity.ProductImage;

public record ImageUpload(
    String photoUrl
) {
    public ProductImage toEntity(Long productId) {
        return ProductImage.builder()
                .productId(productId)
                .photoUrl(photoUrl)
                .build();
            }
        }
