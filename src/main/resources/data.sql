-- OWNER 유저 2명
INSERT IGNORE INTO users (kakao_id, nickname, email, role) VALUES
  ('kakao_owner_001', '김채연', 'owner1@test.com', 'ROLE_OWNER'),
  ('kakao_owner_002', '박보검', 'owner2@test.com', 'ROLE_OWNER'),
  ('kakao_owner_003', '김민주', 'owner3@test.com', 'ROLE_OWNER'),
  ('kakao_owner_004', '김세정', 'owner4@test.com', 'ROLE_OWNER');

-- DIRECTOR 유저 1명
INSERT IGNORE INTO users (kakao_id, nickname, email, role) VALUES
  ('kakao_director_001', '원장박행복', 'director1@test.com', 'ROLE_DIRECTOR');

-- 강아지 3마리
INSERT IGNORE INTO dogs (owner_id, name, breed) VALUES
  (1, '초코', '말티즈'),
  (1, '콩이', '포메라니안'),
  (2, '뭉치', '골든리트리버');

-- 유치원 1개
INSERT IGNORE INTO kindergartens (director_id, name, address) VALUES
  (3, '행복한 발바닥', '서울시 강남구 테헤란로 123');

-- 원생 등록 2건
INSERT IGNORE INTO enrollments (dog_id, kindergarten_id, enrolled_date) VALUES
  (1, 1, '2024-01-10'),
  (2, 1, '2024-01-15');
