package com.example.sungjae_notice.repository;

import com.example.notice_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    // findByUsername을 통해 파라미터 값으로 username 값을 전달
}
