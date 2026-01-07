[spring4]

로그인하지 않고 간단한 글을 쓰고 읽는 게시판
글 작성시 비밀번호를 입력받아 테이블에 같이 저장하고
수정, 삭제시 비밀번호 확인

-- 테이블
create table guestbook (
	num 		integer 	auto_increment 		primary key, 	            -- 글 번호. 기본키. 자동증가
	name		varchar(100) 					not null,		            -- 이름. 문자열. 100문자
	password	varchar(100) 					not null,		            -- 비밀번호. 문자열. 100문자
	message		text 							not null,		            -- 본문내용. 문자열.
	inputdate  	timestamp 						default current_timestamp   -- 시간. 현재시간 자동 저장
);

ALTER TABLE guestbook ADD recommend_cnt INT NOT NULL DEFAULT 0;

-- 추천 테이블
CREATE TABLE guestbook_recommend (
    guestbook_num 	integer 					NOT NULL REFERENCES guestbook(num), -- 글 번호. 외래키.
    ip 				VARCHAR(50) 				NOT NULL,                           -- 접속 IP주소
    created_at 		DATETIME 					NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 시간. 현재시간 자동 저장
    -- 복합 키 (동일 IP + 동일 글 중복 불가)
    PRIMARY KEY (guestbook_num, ip),
    -- FK 제약조건 + ON DELETE CASCADE
    FOREIGN KEY (guestbook_num) REFERENCES guestbook(num) ON DELETE CASCADE
);