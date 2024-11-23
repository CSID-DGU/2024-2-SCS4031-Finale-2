package com.finale.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finale.user.entity.ArtistInfo;
import com.finale.user.entity.StudentArtist;

@Repository
public interface StudentArtistRepository extends JpaRepository<StudentArtist, Long> {
	Optional<StudentArtist> findByArtistInfo(ArtistInfo artistInfo);
}