### 기술 스택
- Java 21
- Spring Boot 3.3.11
- Spring Security 6.3.x (OAuth2 Client + Resource Server)
- MySQL 8.0 + Spring Data JPA
- jjwt 0.12.6
- Gradle 8.x

### 전체 흐름
<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/779a14ee-8e27-43ef-8248-0a3c8c43bfa4" />

### 실행 방법
#### 1. MySQL DB 생성

```sql

CREATE DATABASE knockdog;
```

#### 2. 카카오 개발자 콘솔 설정
#### 3. 환경 변수 설정
```bash

cp .env.example .env

```
| 변수명 | 설명 | 예시 |
|---------|---------|---------|
| DB_NAME | MySQL 데이터베이스 이름 | knockdog |
| DB_USERNAME | MySQL 사용자명 | root |
| DB_PASSWORD | MySQL 비밀번호 | password |
| KAKAO_CLIENT_ID | 카카오 REST API Key | abc123... |
| KAKAO_CLIENT_SECRET | 카카오 Client Secret | xyz789... |
| KAKAO_REDIRECT_URI | Redirect URI | http://localhost:8080/login/oauth2/code/kakao |
| JWT_SECRET | JWT 서명 비밀키 (256bit 이상) | openssl rand -base64 64 |

JWT Secret 생성 예시

```bash

openssl rand -base64 64

```

### 4. 서버 실행

```bash

./gradlew bootRun

```
### API 명세

| Method | URL | 설명 | 주요 응답 |
|----------|----------|----------|----------|
| GET | `/oauth2/authorization/kakao` | 카카오 로그인 시작 | 카카오 로그인 페이지로 이동 |
| POST | `/api/auth/refresh` | 토큰 재발급 | `{ "accessToken": "...", "refreshToken": "..." }` |
| POST | `/api/auth/logout` | 로그아웃 | `{ "message": "Logout Success" }` |
| GET | `/api/home?x={경도}&y={위도}` | OWNER 홈 데이터 조회 | `{ "role": "OWNER", "myDogs": [...], "nearbyKindergartens": [...] }` |
| GET | `/api/home?x={경도}&y={위도}` | DIRECTOR 홈 데이터 조회 | `{ "role": "DIRECTOR", "kindergartenName": "...", "todayDogs": [...], "todayAttendance": 1 }` |
