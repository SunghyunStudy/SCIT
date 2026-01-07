-- spring4.sql

create table guestbook(
    num integer AUTO_INCREMENT PRIMARY KEY,
	name varchar(100) NOT NULL,
	password varchar(100) NOT NULL,
	message text NOT NULL,
	inputdate timestamp DEFAULT CURRENT_TIMESTAMP()
);
ALTER TABLE guestbook ADD recommend_cnt int NOT NULL DEFAULT 0;

CREATE TABLE guestbook_recommend(
	guestbook_num	integer		NOT NULL REFERENCES guestbook(num),
	ip				varchar(50) NOT NULL,
	create_at		DATETIME 	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	-- 복합키 (동일 ip + 동일 글 중복 불가)
	PRIMARY KEY (guestbook_num, ip),
	-- FK 제약조건 + on delete cascade
	FOREIGN KEY (guestbook_num) REFERENCES guestbook(num) ON DELETE CASCADE 
);