package com.finale.product.service.dto;

import com.finale.product.entity.Category;
import com.finale.product.entity.HashTag;
import com.finale.product.entity.Product;
import com.finale.user.entity.ArtistInfo;

import java.util.List;

public record ProductRequest(
        String name,
        String category,
        String size,
        Long price,
        String description,
        String preferredLocation,
        List<HashTag> hashTags,
        Long artistInfo
) {
    public Product toEntity(ArtistInfo artistInfo) {
        return Product.builder()
                .name(name)
                .category(Category.fromString(category))
                .size(size)
                .price(price)
                .description(description)
                .preferredLocation(preferredLocation)
                .hashTags(hashTags)
                .artistInfo(artistInfo)
                .build();
    }
}