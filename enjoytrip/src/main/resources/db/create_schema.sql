use enjoytrip;
CREATE TABLE city (
	city_code INT NOT NULL AUTO_INCREMENT,
	city_name VARCHAR(255) NOT NULL,
	PRIMARY KEY (city_code)
);

CREATE TABLE town
(
    town_code INT NOT NULL AUTO_INCREMENT,
    town_name VARCHAR(30) NOT NULL,
    city_code INT NOT NULL,
    PRIMARY KEY (town_code, city_code),
    CONSTRAINT foreign_key_town_city_code FOREIGN KEY (city_code) REFERENCES city (city_code) ON DELETE CASCADE
);

CREATE TABLE members (
    member_id BIGINT(11) NOT NULL AUTO_INCREMENT,
    member_name VARCHAR(255) NOT NULL,
    member_email VARCHAR(255) NOT NULL,
    member_pwd VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    profile_image VARCHAR(255) NULL,
    city_code INT NULL,
    town_code INT NULL,
    provider_type ENUM('LOCAL', 'GOOGLE', 'KAKAO', 'NAVER') NOT NULL DEFAULT 'LOCAL',
    role_type ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
    is_email_verified TINYINT(1) NOT NULL DEFAULT 0,
    is_locked TINYINT(1) NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (member_id),
    FOREIGN KEY (city_code) REFERENCES city (city_code) ON DELETE SET NULL,
    FOREIGN KEY (town_code, city_code) REFERENCES town (town_code, city_code) ON DELETE SET NULL
);

CREATE TABLE plan (
    plan_id BIGINT NOT NULL AUTO_INCREMENT,
    plan_title VARCHAR(255) NOT NULL,
    member_id BIGINT NOT NULL,
    thumbnail_image VARCHAR(255),
    start_date DATE,
    end_date DATE,
    city_code INT NOT NULL,
    `day` INT DEFAULT 0 NOT NULL,
    PRIMARY KEY (plan_id),
    FOREIGN KEY (member_id) REFERENCES members (member_id),
    FOREIGN KEY (city_code) REFERENCES city (city_code)
);

CREATE TABLE tour_content (
    content_id BIGINT NOT NULL AUTO_INCREMENT,
    content_name ENUM('TOURIST_SPOT', 'STAY', 'RESTAURANT', 'CULTURE', 'SHOW', 'TRAVEL', 'SHOPPING', 'LEISURE') NOT NULL,
    kor_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (content_id)
);

CREATE TABLE tour (
    tour_id BIGINT NOT NULL AUTO_INCREMENT,
    content_id BIGINT,
    tour_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    zip_code VARCHAR(255),
    background_image VARCHAR(255),
    city_code INT,
    town_code INT,
    hit INT DEFAULT 0,
    PRIMARY KEY (tour_id),
    FOREIGN KEY (content_id) REFERENCES tour_content (content_id),
    FOREIGN KEY (city_code) REFERENCES city (city_code),
    FOREIGN KEY (town_code, city_code) REFERENCES town (town_code, city_code)
);

CREATE TABLE tour_detail (
    tour_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    telephone VARCHAR(255),
    latitude DECIMAL(11,8),
    longitude DECIMAL(11,8),
    PRIMARY KEY (tour_id),
    FOREIGN KEY (tour_id) REFERENCES tour (tour_id)
);

CREATE TABLE plan_trip (
    plan_trip_id BIGINT NOT NULL AUTO_INCREMENT,
    plan_id BIGINT NOT NULL,
    tour_id BIGINT NOT NULL,
    day INT NOT NULL,
    `order` INT NOT NULL,
    PRIMARY KEY (plan_trip_id),
    FOREIGN KEY (plan_id) REFERENCES plan (plan_id),
    FOREIGN KEY (tour_id) REFERENCES tour (tour_id)
);

CREATE TABLE notice
(
    notice_id  INT AUTO_INCREMENT PRIMARY KEY,
    notice_title VARCHAR(255) NOT NULL,
    notice_content text NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE reviews
(
    review_id  INT AUTO_INCREMENT PRIMARY KEY,
    review_title VARCHAR(255) NOT NULL,
    review_content text NOT NULL,
    member_id BIGINT(11) NOT NULL,
    tour_id BIGINT NOT NULL,
    review_image VARCHAR(500) NOT NULL,
    created_at datetime DEFAULT CURRENT_TIMESTAMP NULL,
    like_count INT DEFAULT 0 NULL,
    rating INT NULL,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP NOT NULL ON
        UPDATE
        CURRENT_TIMESTAMP,
    FOREIGN KEY (tour_id) REFERENCES tour (tour_id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members (member_id) ON DELETE CASCADE,
    CHECK (
        `rating` BETWEEN 1 AND 5
        )
);

CREATE TABLE review_like
(
    review_id INT NOT NULL,
    member_id   BIGINT(11) NOT NULL,
    PRIMARY KEY (review_id, member_id),
    FOREIGN KEY (review_id) REFERENCES reviews (review_id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members (member_id) ON DELETE CASCADE
);

CREATE TABLE review_comment
(
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    review_id  INT NOT NULL,
    content text NOT NULL,
    member_id BIGINT(11) NOT NULL,
    created_at datetime DEFAULT CURRENT_TIMESTAMP NULL,
    FOREIGN KEY (review_id) REFERENCES reviews (review_id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members (member_id) ON DELETE CASCADE
);

CREATE TABLE chat_bot
(
    chat_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT(11) NOT NULL,
    user_request VARCHAR(500) NOT NULL,
    ai_response text NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members (member_id) ON DELETE CASCADE
);