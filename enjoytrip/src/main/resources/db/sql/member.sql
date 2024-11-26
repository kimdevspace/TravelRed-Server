insert into enjoytrip.members (member_id, member_name, member_email, member_pwd, nickname, profile_image, city_code, town_code, provider_type, role_type, is_email_verified, is_locked, created_at, updated_at)
values
    (1, '김싸피', 'ssafy@ssafy.com', '{noop}1234', '김싸피', null,
    3, 3, 'LOCAL', 'ADMIN', 1, 0,
    '2024-05-23 03:17:16', '2024-05-23 13:42:22'),

    (2, '이지은', 'jieun@gmail.com', '{noop}1234', '아이유', null,
    1, 5, 'LOCAL', 'USER', 1, 0,
    '2024-05-23 10:15:22', '2024-05-23 10:15:22'),

    (3, '박재범', 'jay@naver.com', '{noop}1234', 'jaypark', null,
    6, 13, 'LOCAL', 'USER', 1, 0,
    '2024-05-23 11:30:45', '2024-05-23 11:30:45'),

    (4, '김태리', 'taeri@kakao.com', '{noop}1234', '태리태리', null,
    31, 26, 'LOCAL', 'USER', 1, 0,
    '2024-05-23 14:22:33', '2024-05-23 14:22:33'),

    (5, '정호석', 'jhope@gmail.com', '{noop}1234', 'hobi', null,
    5, 4, 'LOCAL', 'USER', 1, 0,
    '2024-05-23 16:45:12', '2024-05-23 16:45:12'),

    (6, '강민경', 'davichi@naver.com', '{noop}1234', '다비치', null,
    35, 8, 'LOCAL', 'USER', 1, 0,
    '2024-05-23 18:10:55', '2024-05-23 18:10:55');