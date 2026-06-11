-- =============================================
-- USERS: OWNER 20명 + DIRECTOR 5명
-- =============================================
INSERT INTO users (kakao_id, nickname, email, role) VALUES
  ('kakao_owner_001', '김민준', 'owner001@test.com', 'ROLE_OWNER'),
  ('kakao_owner_002', '이서연', 'owner002@test.com', 'ROLE_OWNER'),
  ('kakao_owner_003', '박도윤', 'owner003@test.com', 'ROLE_OWNER'),
  ('kakao_owner_004', '최지아', 'owner004@test.com', 'ROLE_OWNER'),
  ('kakao_owner_005', '정시우', 'owner005@test.com', 'ROLE_OWNER'),
  ('kakao_owner_006', '강하은', 'owner006@test.com', 'ROLE_OWNER'),
  ('kakao_owner_007', '윤준서', 'owner007@test.com', 'ROLE_OWNER'),
  ('kakao_owner_008', '장수아', 'owner008@test.com', 'ROLE_OWNER'),
  ('kakao_owner_009', '임지호', 'owner009@test.com', 'ROLE_OWNER'),
  ('kakao_owner_010', '한소율', 'owner010@test.com', 'ROLE_OWNER'),
  ('kakao_owner_011', '오예준', 'owner011@test.com', 'ROLE_OWNER'),
  ('kakao_owner_012', '서채원', 'owner012@test.com', 'ROLE_OWNER'),
  ('kakao_owner_013', '신지훈', 'owner013@test.com', 'ROLE_OWNER'),
  ('kakao_owner_014', '권나연', 'owner014@test.com', 'ROLE_OWNER'),
  ('kakao_owner_015', '황민서', 'owner015@test.com', 'ROLE_OWNER'),
  ('kakao_owner_016', '안서준', 'owner016@test.com', 'ROLE_OWNER'),
  ('kakao_owner_017', '송지우', 'owner017@test.com', 'ROLE_OWNER'),
  ('kakao_owner_018', '홍예원', 'owner018@test.com', 'ROLE_OWNER'),
  ('kakao_owner_019', '문준혁', 'owner019@test.com', 'ROLE_OWNER'),
  ('kakao_owner_020', '배수빈', 'owner020@test.com', 'ROLE_OWNER');

INSERT INTO users (kakao_id, nickname, email, role) VALUES
  ('kakao_director_001', '원장김행복', 'director001@test.com', 'ROLE_DIRECTOR'),
  ('kakao_director_002', '원장이사랑', 'director002@test.com', 'ROLE_DIRECTOR'),
  ('kakao_director_003', '원장박희망', 'director003@test.com', 'ROLE_DIRECTOR'),
  ('kakao_director_004', '원장최기쁨', 'director004@test.com', 'ROLE_DIRECTOR'),
  ('kakao_director_005', '원장정평화', 'director005@test.com', 'ROLE_DIRECTOR');

-- =============================================
-- KINDERGARTENS: 5개 (director id: 21~25)
-- =============================================
INSERT INTO kindergartens (director_id, name, address) VALUES
  (21, '행복한 발바닥',       '서울시 강남구 테헤란로 123'),
  (22, '사랑스러운 꼬리',     '서울시 서초구 반포대로 45'),
  (23, '희망 멍멍유치원',     '서울시 송파구 올림픽로 67'),
  (24, '기쁨 왈왈유치원',     '서울시 마포구 홍대입구로 89'),
  (25, '평화로운 퍼피하우스', '서울시 용산구 이태원로 101');

-- =============================================
-- DOGS: 보호자 1명당 2마리 (총 30마리)
-- =============================================
INSERT INTO dogs (owner_id, name, breed) VALUES
  (1,  '초코',   '말티즈'),       (1,  '콩이',   '포메라니안'),
  (2,  '뭉치',   '골든리트리버'), (2,  '보리',   '비숑프리제'),
  (3,  '두부',   '시츄'),         (3,  '하루',   '프렌치불독'),
  (4,  '코코',   '푸들'),         (4,  '구름',   '사모예드'),
  (5,  '달이',   '치와와'),       (5,  '별이',   '닥스훈트'),
  (6,  '솜이',   '스피츠'),       (6,  '눈이',   '허스키'),
  (7,  '봄이',   '진돗개'),       (7,  '여름',   '삽살개'),
  (8,  '가을',   '말티즈'),       (8,  '겨울',   '포메라니안'),
  (9,  '해피',   '골든리트리버'), (9,  '럭키',   '래브라도'),
  (10, '피치',   '비숑프리제'),   (10, '망고',   '시바이누'),
  (11, '레오',   '보더콜리'),     (11, '루나',   '말티즈'),
  (12, '맥스',   '푸들'),         (12, '벨라',   '치와와'),
  (13, '로키',   '허스키'),       (13, '몰리',   '비숑프리제'),
  (14, '버디',   '골든리트리버'), (14, '데이지', '푸들'),
  (15, '올리',   '프렌치불독'),   (15, '팝피',   '포메라니안');

-- =============================================
-- ENROLLMENTS: 총 20건
-- =============================================
INSERT INTO enrollments (dog_id, kindergarten_id, enrolled_date) VALUES
  (1,  1, '2024-01-05'), (2,  1, '2024-01-08'), (3,  1, '2024-01-10'),
  (4,  1, '2024-01-12'), (5,  1, '2024-01-15'),
  (6,  2, '2024-02-01'), (7,  2, '2024-02-03'), (8,  2, '2024-02-05'),
  (9,  2, '2024-02-07'), (10, 2, '2024-02-09'),
  (11, 3, '2024-03-01'), (12, 3, '2024-03-03'), (13, 3, '2024-03-05'),
  (14, 3, '2024-03-07'),
  (15, 4, '2024-04-01'), (16, 4, '2024-04-03'), (17, 4, '2024-04-05'),
  (18, 5, '2024-05-01'), (19, 5, '2024-05-03'), (20, 5, '2024-05-05');
