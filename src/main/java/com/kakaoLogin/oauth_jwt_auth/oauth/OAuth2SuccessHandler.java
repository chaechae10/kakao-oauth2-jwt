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

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ObjectMapper objectMapper;
    private final HttpSessionOAuth2AuthorizationRequestRepository authorizationRequestRepository;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(userDetails.getUserId(), userDetails.getRole());
        String refreshToken = jwtService.generateRefreshToken(userDetails.getUserId());

        refreshTokenRepository.deleteByUserId(userDetails.getUserId());

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .userId(userDetails.getUserId())
                .token(refreshToken)
                .expiresAt(LocalDateTime.now().plusSeconds(refreshTokenExpiration / 1000))
                .build();
        refreshTokenRepository.save(refreshTokenEntity);

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
