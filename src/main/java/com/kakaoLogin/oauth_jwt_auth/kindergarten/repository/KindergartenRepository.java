package com.kakaoLogin.oauth_jwt_auth.kindergarten.repository;

import com.kakaoLogin.oauth_jwt_auth.kindergarten.entity.Kindergarten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KindergartenRepository extends JpaRepository<Kindergarten, Long> {

    Optional<Kindergarten> findByDirectorId(Long directorId);

    @Query(value = "SELECT id, director_id, name, address, latitude, longitude " +
            "FROM kindergartens k " +
            "WHERE MBRContains(ST_LINESTRINGFROMTEXT(:pointFormat, 0), k.point) " +
            "LIMIT 10",
            nativeQuery = true)
    List<Kindergarten> findNearby(@Param("pointFormat") String pointFormat);
}
