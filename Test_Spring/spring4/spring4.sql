-- spring4.sql

create table guestbook(
    num integer AUTO_INCREMENT PRIMARY KEY,
	name varchar(100) NOT NULL,
	password varchar(100) NOT NULL,
	message text NOT NULL,
	inputdate timestamp DEFAULT CURRENT_TIMESTAMP
);