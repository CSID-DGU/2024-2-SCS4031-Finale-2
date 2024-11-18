package com.finale.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finale.user.entity.Social;

public interface SocialRepository extends JpaRepository<Social, Long> {
}