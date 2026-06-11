package com.kakaoLogin.oauth_jwt_auth.user.repository;

import com.kakaoLogin.oauth_jwt_auth.user.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findAllByKindergartenId(Long kindergartenId);
}
