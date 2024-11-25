package com.finale.review.service;
import com.finale.product.entity.Product;
import com.finale.product.repository.ProductRepository;
import com.finale.review.dto.ReviewRequest;
import com.finale.review.entity.Review;
import com.finale.review.repository.ReviewRepository;
import com.finale.user.entity.User;
import com.finale.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public void saveReview(ReviewRequest request, Long productId) {
        User writer = userRepository.findById(request.writerId()).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 writerID입니다."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 productID입니다."));
        reviewRepository.save(request.toEntity(writer,product));
    }
    public void editReview(ReviewRequest request, Long productId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 reviewId 입니다."));
        review.updateContent(request.content());
    }
 }