package com.kakaoLogin.oauth_jwt_auth.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaoLogin.oauth_jwt_auth.auth.dto.TokenResponse;
import com.kakaoLogin.oauth_jwt_auth.auth.entity.RefreshToken;
import com.kakaoLogin.oauth_jwt_auth.auth.repository.RefreshTokenRepository;
import com.kakaoLogin.oauth_jwt_auth.auth.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ObjectMapper objectMapper;

    // 순환 참조 방지를 위해 SecurityConfig의 빈을 주입받지 않고 직접 생성
    private final HttpSessionOAuth2AuthorizationRequestRepository authorizationRequestRepository =
            new HttpSessionOAuth2AuthorizationRequestRepository();

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(userDetails.getUserId(), userDetails.getRole());
        String refreshToken = jwtService.generateRefreshToken(userDetails.getUserId());

        // 기존 토큰 삭제 후 새 토큰 저장 (트랜잭션으로 묶여 있어 저장 실패 시 삭제도 롤백됨)
        refreshTokenRepository.deleteByUserId(userDetails.getUserId());
        refreshTokenRepository.save(RefreshToken.builder()
                .userId(userDetails.getUserId())
                .token(refreshToken)
                .expiresAt(LocalDateTime.now().plusSeconds(refreshTokenExpiration / 1000))
                .build());

        TokenResponse tokenResponse = TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .role(userDetails.getRole().name())
                .build();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(tokenResponse));

        authorizationRequestRepository.removeAuthorizationRequest(request, response);
    }
}
