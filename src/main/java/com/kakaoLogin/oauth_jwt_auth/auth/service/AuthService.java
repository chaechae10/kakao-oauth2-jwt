package com.kakaoLogin.oauth_jwt_auth.auth.service;

import com.kakaoLogin.oauth_jwt_auth.auth.dto.TokenResponse;
import com.kakaoLogin.oauth_jwt_auth.auth.entity.RefreshToken;
import com.kakaoLogin.oauth_jwt_auth.auth.repository.RefreshTokenRepository;
import com.kakaoLogin.oauth_jwt_auth.exception.BusinessException;
import com.kakaoLogin.oauth_jwt_auth.exception.ErrorCode;
import com.kakaoLogin.oauth_jwt_auth.user.entity.User;
import com.kakaoLogin.oauth_jwt_auth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public TokenResponse refresh(String refreshToken) {
        if (!jwtService.validateToken(refreshToken)) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }

        RefreshToken savedToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_NOT_FOUND));

        if (savedToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BusinessException(ErrorCode.EXPIRED_TOKEN);
        }

        Long userId = jwtService.extractUserId(refreshToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        String newAccessToken = jwtService.generateAccessToken(userId, user.getRole());
        String newRefreshToken = jwtService.generateRefreshToken(userId);

        refreshTokenRepository.deleteByUserId(userId);
        refreshTokenRepository.save(RefreshToken.builder()
                .userId(userId)
                .token(newRefreshToken)
                .expiresAt(LocalDateTime.now().plusSeconds(
                        jwtService.getRefreshTokenExpiration() / 1000))
                .build());

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .role(user.getRole().name())
                .build();
    }

    public void logout(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}
