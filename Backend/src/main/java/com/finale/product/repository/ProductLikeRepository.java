package com.finale.product.repository;

import com.finale.product.entity.Like;
import com.finale.product.entity.Product;
import com.finale.user.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findDistinctFirstByUserAndProduct(User user, Product product);

    Page<Like> findAllByUser(User user, Pageable pageable);
}
