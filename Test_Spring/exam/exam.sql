USE springjpa;

DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS answer;

-- 1. 정답 테이블
CREATE TABLE answer (
    num INT PRIMARY KEY,
    correct_answer VARCHAR(50)
);

-- 2. 학생 응시정보 테이블 생성
CREATE TABLE student (
    seq INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL, 
    email VARCHAR(100) NOT NULL, 
    password VARCHAR(50) NOT NULL,
    
    answer1 VARCHAR(50),
    answer2 VARCHAR(50),
    answer3 VARCHAR(50),
    answer4 VARCHAR(50),
    answer5 VARCHAR(50),
    
    score INT DEFAULT 0,            
    exam_date DATETIME DEFAULT NOW()   
);

-- 3. 정답 데이터
INSERT INTO answer (num, correct_answer) VALUES (1, '2');
INSERT INTO answer (num, correct_answer) VALUES (2, '3');
INSERT INTO answer (num, correct_answer) VALUES (3, '1');
INSERT INTO answer (num, correct_answer) VALUES (4, 'extends');
INSERT INTO answer (num, correct_answer) VALUES (5, 'try');

-- 4. 학생 응시정보 테스트 데이터
INSERT INTO student (name, email, password, answer1, answer2, answer3, answer4, answer5, score, exam_date)
VALUES ('홍길동', 'hong@test.com', '1234', '2', '3', '1', 'extends', 'try', 100, '2025-12-21 11:00:00');

INSERT INTO student (name, email, password, answer1, answer2, answer3, answer4, answer5, score, exam_date)
VALUES ('김영희', 'kim@test.com', '1111', '2', '3', '1', 'extends', 'catch', 80, '2025-12-21 17:00:51');

INSERT INTO student (name, email, password, answer1, answer2, answer3, answer4, answer5, score, exam_date)
VALUES ('이철수', 'lee@test.com', '2222', '1', '3', '1', 'extends', 'finally', 60, '2025-12-22 17:00:40');

INSERT INTO student (name, email, password, answer1, answer2, answer3, answer4, answer5, score, exam_date)
VALUES ('박민수', 'park@test.com', '3333', '4', '1', '2', 'extends', 'try', 40, '2025-12-23 09:30:00');

INSERT INTO student (name, email, password, answer1, answer2, answer3, answer4, answer5, score, exam_date)
VALUES ('최자바', 'choi@test.com', '4444', '1', '1', '1', 'extends', 'throw', 20, '2025-12-24 14:20:10');

COMMIT;