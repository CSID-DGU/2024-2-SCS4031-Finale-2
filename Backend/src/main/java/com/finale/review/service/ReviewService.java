package com.finale.review.service;

import com.finale.product.entity.Product;
import com.finale.product.repository.ProductRepository;
import com.finale.review.dto.ReviewRequest;
import com.finale.review.dto.ReviewResponse;
import com.finale.review.entity.Review;
import com.finale.review.repository.ReviewRepository;
import com.finale.user.entity.ArtistInfo;
import com.finale.user.entity.User;
import com.finale.user.repository.ArtistInfoRepository;
import com.finale.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ArtistInfoRepository artistInfoRepository;

    @Transactional
    public void saveReview(ReviewRequest request, Long productId) {
        User writer = userRepository.findById(request.writerId()).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 writerID입니다."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 productID입니다."));

        reviewRepository.save(request.toEntity(writer,product));
    }

    @Transactional
    public void editReview(ReviewRequest request, Long productId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 reviewId 입니다."));
        review.updateContent(request.content());
    }

    @Transactional(readOnly = true)
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 reviewId 입니다."));
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> getAllReviewByProduct(Long productId, Pageable pageable) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 productId입니다." + productId));
        return reviewRepository.findAllByProduct(product,pageable).map(ReviewResponse::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> getAllReviewByArtist(Long artistId, Pageable pageable) {
        ArtistInfo artistInfo = artistInfoRepository.findById(artistId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 ArtistId입니다." + artistId));
        return reviewRepository.findAllByArtistInfo(artistInfo,pageable).map(ReviewResponse::fromEntity);
    }
 }