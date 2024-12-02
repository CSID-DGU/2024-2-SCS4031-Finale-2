package com.finale.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finale.user.entity.ArtistInfo;
import com.finale.user.entity.BusinessArtist;

public interface BusinessArtistRepository extends JpaRepository<BusinessArtist, Long> {
	Optional<BusinessArtist> findByArtistInfo(ArtistInfo artistInfo);
}