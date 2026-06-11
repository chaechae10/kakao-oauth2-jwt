package com.kakaoLogin.oauth_jwt_auth.user.repository;

import com.kakaoLogin.oauth_jwt_auth.user.entity.Kindergarten;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KindergartenRepository extends JpaRepository<Kindergarten, Long> {

    Optional<Kindergarten> findByDirectorId(Long directorId);
}
