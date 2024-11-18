package com.finale.product.service;

import com.finale.product.dto.ProductSaveRequest;
import com.finale.product.entity.Product;
import com.finale.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(ProductSaveRequest productSaveRequest) {
        //TODO ArtistInfo 코드 병합시 수정 예정
        Product product = productSaveRequest.toEntity(null);
        productRepository.save(product);
        return product;
    }
    
    public Product find(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
    }

    @Transactional
    public void edit(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
        //TODO ArtistInfo 코드 병합시 수정 예정
        product.update(
                productRequest.name(),
                Category.fromString(productRequest.category()),
                productRequest.size(),
                productRequest.price(),
                productRequest.description(),
                productRequest.preferredLocation(),
                productRequest.hashTags(),
                null);
    }

    public void delete(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다"));
        productRepository.deleteById(productId);
    }
}