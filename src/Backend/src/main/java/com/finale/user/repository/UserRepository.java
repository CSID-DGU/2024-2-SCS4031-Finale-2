package com.finale.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finale.user.entity.User;
import com.finale.user.repository.querydsl.UserCustomRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
	Optional<User> findById(Long id);
}