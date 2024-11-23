
package com.finale.product.repository;

import com.finale.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
}