# oauth-jwt-auth

카카오 OAuth2 로그인 + JWT 인증 서버

## 기술 스택
- Java 21
- Spring Boot 3.3.11
- Spring Security 6.3.x (OAuth2 Client + Resource Server)
- MySQL 8.0 + Spring Data JPA
- jjwt 0.12.6
- Gradle 8.x

## 브랜치 전략
```
main ← epic/auth ← feature/*
```

## 환경 설정
`.env.example`을 참고하여 `.env` 파일을 생성

```bash
cp .env.example .env
```
