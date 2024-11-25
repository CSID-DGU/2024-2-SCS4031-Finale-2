package com.finale.product.service;

import com.finale.product.dto.ProductRequest;
import com.finale.product.entity.Category;
import com.finale.product.entity.Product;
import com.finale.product.repository.ProductImageRepository;
import com.finale.product.repository.ProductRepository;
import com.finale.user.entity.ArtistInfo;
import com.finale.user.repository.ArtistInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ArtistInfoRepository artistInfoRepository;

    public Product save(ProductRequest productSaveRequest) {
        ArtistInfo artistInfo = artistInfoRepository.findById(productSaveRequest.artistInfoId())
        .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 작가 정보입니다."));
Product product = productSaveRequest.toEntity(artistInfo);
        productRepository.save(product);
        return product;
    }

    public Product find(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
    }

    @Transactional
    public void edit(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
        ArtistInfo artistInfo = artistInfoRepository.findById(productRequest.artistInfoId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 작가 정보입니다."));
        product.update(
                productRequest.name(),
                Category.fromString(productRequest.category()),
                productRequest.size(),
                productRequest.price(),
                productRequest.description(),
                productRequest.preferredLocation(),
                productRequest.hashTags(),
                artistInfo);
    }

    public void delete(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
        productRepository.delete(product);
        productImageRepository.deleteAllByProductId(productId);
    }
}