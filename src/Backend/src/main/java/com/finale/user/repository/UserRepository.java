
package com.finale.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.finale.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    import java.util.Optional;
}