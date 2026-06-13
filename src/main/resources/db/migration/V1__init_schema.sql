CREATE TABLE users (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    kakao_id   VARCHAR(50)  UNIQUE NOT NULL,
    nickname   VARCHAR(100),
    email      VARCHAR(200),
    role       ENUM('ROLE_OWNER','ROLE_DIRECTOR') NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE refresh_tokens (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT       NOT NULL,
    token      VARCHAR(512) UNIQUE NOT NULL,
    expires_at DATETIME     NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE dogs (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_id BIGINT      NOT NULL,
    name     VARCHAR(50) NOT NULL,
    breed    VARCHAR(50),
    FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE kindergartens (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    director_id BIGINT       NOT NULL,
    name        VARCHAR(100) NOT NULL,
    address     VARCHAR(255),
    FOREIGN KEY (director_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE enrollments (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    dog_id          BIGINT NOT NULL,
    kindergarten_id BIGINT NOT NULL,
    enrolled_date   DATE,
    FOREIGN KEY (dog_id)          REFERENCES dogs(id)          ON DELETE CASCADE,
    FOREIGN KEY (kindergarten_id) REFERENCES kindergartens(id) ON DELETE CASCADE
);
