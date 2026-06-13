package com.kakaoLogin.oauth_jwt_auth.user.repository;

import com.kakaoLogin.oauth_jwt_auth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByKakaoId(String kakaoId);
}
