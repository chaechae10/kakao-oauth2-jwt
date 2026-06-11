package com.kakaoLogin.oauth_jwt_auth.auth.service;

import com.kakaoLogin.oauth_jwt_auth.user.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    // TODO: feature/jwt 브랜치에서 구현 예정

    public String generateAccessToken(Long userId, Role role) {
        // TODO
        return "stub-access-token";
    }

    public String generateRefreshToken(Long userId) {
        // TODO
        return "stub-refresh-token";
    }

    public boolean validateToken(String token) {
        // TODO
        return false;
    }

    public Long extractUserId(String token) {
        // TODO
        return null;
    }

    public String extractRole(String token) {
        // TODO
        return null;
    }
}
