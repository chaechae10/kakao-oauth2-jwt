package com.kakaoLogin.oauth_jwt_auth.dog.repository;

import com.kakaoLogin.oauth_jwt_auth.dog.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> findAllByOwnerId(Long ownerId);
}
