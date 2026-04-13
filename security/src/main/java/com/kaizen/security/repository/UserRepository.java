package com.kaizen.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kaizen.security.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
        Optional<User> findByUsername(String username);
}
