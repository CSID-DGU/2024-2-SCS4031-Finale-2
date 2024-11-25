package com.finale.review.repository;

import com.finale.product.entity.Product;
import com.finale.review.entity.Review;
import com.finale.user.entity.ArtistInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByProduct(Product product, Pageable pageable);

    @Query("SELECT r FROM Review r JOIN r.product p JOIN p.artistInfo a where a = :artistInfo")
    Page<Review> findAllByArtistInfo(@Param("artistInfo") ArtistInfo artistInfo, Pageable pageable);
}