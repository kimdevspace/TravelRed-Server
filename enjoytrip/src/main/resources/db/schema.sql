CREATE TABLE city (
                      city_code INT NOT NULL,
                      citiy_name VARCHAR(255) NOT NULL,
                      PRIMARY KEY (city_code)
);

CREATE TABLE town (
                      town_code BIGINT(11) NOT NULL,
                      city_code INT NOT NULL,
                      town_name VARCHAR(255) NOT NULL,
                      PRIMARY KEY (town_code, city_code),
                      FOREIGN KEY (city_code) REFERENCES city (city_code)
);

CREATE TABLE members (
                         member_id BIGINT(11) NOT NULL AUTO_INCREMENT,
                         member_name VARCHAR(255) NOT NULL,
                         member_email VARCHAR(255) NOT NULL,
                         member_pwd VARCHAR(255) NOT NULL,
                         nickname VARCHAR(255) NOT NULL,
                         profile_image VARCHAR(255) NULL,
                         city_code INT NULL,
                         town_code BIGINT(11) NULL,
                         provider_type ENUM('LOCAL', 'GOOGLE', 'KAKAO', 'NAVER') NOT NULL DEFAULT 'LOCAL',
                         role_type ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
                         is_email_verified TINYINT(1) NOT NULL DEFAULT 0,
                         is_locked TINYINT(1) NOT NULL DEFAULT 0,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (member_id),
                         FOREIGN KEY (city_code) REFERENCES city (city_code) ON DELETE SET NULL,
                         FOREIGN KEY (town_code) REFERENCES town (town_code) ON DELETE SET NULL
);