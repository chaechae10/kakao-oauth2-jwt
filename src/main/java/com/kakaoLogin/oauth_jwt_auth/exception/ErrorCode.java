package com.kakaoLogin.oauth_jwt_auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 Refresh Token"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 Refresh Token"),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "존재하지 않는 Refresh Token"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자");

    private final HttpStatus status;
    private final String message;
}
