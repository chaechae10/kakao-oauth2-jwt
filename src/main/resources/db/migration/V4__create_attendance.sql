CREATE TABLE attendance (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    dog_id          BIGINT NOT NULL,
    kindergarten_id BIGINT NOT NULL,
    attended_date   DATE   NOT NULL,
    FOREIGN KEY (dog_id)          REFERENCES dogs(id)          ON DELETE CASCADE,
    FOREIGN KEY (kindergarten_id) REFERENCES kindergartens(id) ON DELETE CASCADE
);

-- 오늘 출석 더미 데이터 (CURDATE() 기준)
INSERT INTO attendance (dog_id, kindergarten_id, attended_date) VALUES
  (1, 1, CURDATE()),
  (2, 1, CURDATE()),
  (3, 1, CURDATE()),
  (6, 2, CURDATE()),
  (7, 2, CURDATE()),
  (11, 3, CURDATE()),
  (12, 3, CURDATE()),
  (13, 3, CURDATE()),
  (15, 4, CURDATE()),
  (18, 5, CURDATE()),
  (19, 5, CURDATE());
