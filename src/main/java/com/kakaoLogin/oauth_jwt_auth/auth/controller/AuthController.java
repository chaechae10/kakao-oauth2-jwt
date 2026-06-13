package com.kakaoLogin.oauth_jwt_auth.auth.controller;

import com.kakaoLogin.oauth_jwt_auth.auth.dto.RefreshRequest;
import com.kakaoLogin.oauth_jwt_auth.auth.dto.TokenResponse;
import com.kakaoLogin.oauth_jwt_auth.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest request) {
        TokenResponse tokenResponse = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        authService.logout(userId);
        return ResponseEntity.ok(Map.of("message", "로그아웃 되었습니다."));
    }
}
