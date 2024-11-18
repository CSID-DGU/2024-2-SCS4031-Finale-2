package com.finale.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finale.user.entity.BusinessArtist;

public interface BusinessArtistRepository extends JpaRepository<BusinessArtist, Long> {
}