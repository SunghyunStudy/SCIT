-- Database Creation (If not exists)
CREATE DATABASE IF NOT EXISTS prac_db;
USE prac_db;

-- Member Table
CREATE TABLE IF NOT EXISTS member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER'
);

-- Game History Table
CREATE TABLE IF NOT EXISTS game_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    winner_id BIGINT NOT NULL,
    loser_id BIGINT NOT NULL,
    played_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    CONSTRAINT fk_game_winner FOREIGN KEY (winner_id) REFERENCES member (id),
    CONSTRAINT fk_game_loser FOREIGN KEY (loser_id) REFERENCES member (id)
);
