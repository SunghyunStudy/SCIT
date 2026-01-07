-- 회원정보 테이블
create table spring5_member (
	 member_id varchar(30) primary key,          -- 회원을 구분하는 아이디
	 member_password varchar(100) not null,      -- 비밀번호(암호화)
	 member_name varchar(30) not null,           -- 회원 이름
	 email varchar(50),                          -- 이메일
	 phone varchar(30),                          -- 전화번호
	 address varchar(200),                       -- 주소
	 enabled tinyint(1) default 1 check(enabled in (0, 1)),                                     -- 계정상태. 1:사용가능, 0:사용불가능
	 rolename varchar(30) default 'ROLE_USER'
	 check (rolename in ('ROLE_USER', 'ROLE_ADMIN'))   -- 사용자 구분. 'role_user', 'role_admin' 중 하나
);

-- 게시판 테이블
create table spring5_board (
    board_num int auto_increment primary key,    -- 게시글 일련번호
    member_id varchar(30)						 -- 작성자 id (외래 키)
    	references spring5_member (member_id) on delete set null,
    title varchar(1000) not null,                -- 글제목
    contents text not null,                      -- 글내용
    view_count int default 0,                    -- 조회수
    like_count int default 0,                    -- 추천수
    original_name varchar(300),                         -- 첨부파일 원래 이름
    file_name varchar(100),                             -- 첨부파일 저장된 이름
    create_date timestamp default current_timestamp,    -- 작성 시간
    update_date timestamp default current_timestamp
    			on update current_timestamp  		    -- 수정 시간
);

-- 댓글 테이블
CREATE TABLE spring5_reply (
    reply_num integer AUTO_INCREMENT PRIMARY KEY,		-- 댓글 일련번호
    -- 부모 행 삭제 시 자식의 외래키 값을 NULL로 설정, 회원 탈퇴 시 댓글 작성자만 NULL 처리
    member_id 	VARCHAR(255)						    -- 작성자 id (외래 키)
    	REFERENCES spring5_member(member_id) ON DELETE SET NULL,
    contents 	VARCHAR(2000) 	NOT NULL,			    -- 댓글 내용
    create_date TIMESTAMP 		DEFAULT 		CURRENT_TIMESTAMP,	-- 작성 시간
    -- 부모 행 삭제 시 자식 행도 함께 삭제됨, 게시글 삭제 시 댓글도 같이 삭제
    board_num	integer 							    -- 글번호 (외래 키)
    	REFERENCES spring5_board(board_num) ON DELETE cascade
);