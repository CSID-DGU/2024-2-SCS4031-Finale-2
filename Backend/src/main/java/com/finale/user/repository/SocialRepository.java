package com.finale.user.repository;

import com.finale.user.entity.ArtistInfo;
import com.finale.user.entity.Social;
import com.finale.user.entity.User;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends JpaRepository<Social, Long> {
	Boolean existsByFollowerAndFollowing(User follower, ArtistInfo following);
	Boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
	Optional<Social> findByFollowerAndFollowing(User follower, ArtistInfo following);

	@Query("SELECT s.following.id FROM Social s WHERE s.follower.id = :userId")
	Set<Long> findFollowingIdByFollowerId(Long userId);
}