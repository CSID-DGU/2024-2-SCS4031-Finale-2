package com.finale.product.service;

import com.finale.global.utils.AwsS3FileUtils;
import com.finale.product.entity.ProductImage;
import com.finale.product.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final AwsS3FileUtils awsS3FileUtils;
    private final ProductImageRepository productImageRepository;
    
    @Transactional
    public List<String> uploadMultiFiles(Long productId, List<MultipartFile> files) throws IOException {
        List<String> urlList = awsS3FileUtils.uploadMultiImages(files);
        urlList.forEach(fileUrl -> {
            ProductImage productImage = new ProductImage(fileUrl,productId);
            productImageRepository.save(productImage);
        });
        return urlList;
    }
}