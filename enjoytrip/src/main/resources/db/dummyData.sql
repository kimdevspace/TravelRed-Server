-- City 데이터 (광역시/도)
INSERT INTO city (city_code, city_name) VALUES
                                            (1, '서울특별시'),
                                            (2, '부산광역시'),
                                            (3, '대구광역시'),
                                            (4, '인천광역시'),
                                            (5, '광주광역시'),
                                            (6, '대전광역시'),
                                            (7, '울산광역시'),
                                            (8, '세종특별자치시'),
                                            (9, '경기도'),
                                            (10, '강원도');

-- Town 데이터 (구/군)
INSERT INTO town (town_code, town_name, city_code) VALUES
                                                       (1, '강남구', 1),
                                                       (2, '서초구', 1),
                                                       (3, '해운대구', 2),
                                                       (4, '수성구', 3),
                                                       (5, '연수구', 4),
                                                       (6, '광산구', 5),
                                                       (7, '유성구', 6),
                                                       (8, '남구', 7),
                                                       (9, '분당구', 9),
                                                       (10, '춘천시', 10);

-- Members 데이터
INSERT INTO members
(member_name, member_email, member_pwd, nickname, profile_image, city_code, town_code, provider_type, role_type, is_email_verified)
VALUES
    ('김철수', 'user1@test.com', '{noop}user1234!', '여행왕철수', 'profile1.jpg', 1, 1, 'LOCAL', 'USER', 1),
    ('이영희', 'user2@test.com', '{noop}user1234@', '맛집탐험가', 'profile2.jpg', 2, 3, 'LOCAL', 'USER', 1),
    ('박민수', 'admin@test.com', '{noop}admin1234!', '관리자', 'profile3.jpg', 3, 4, 'LOCAL', 'ADMIN', 1),
    ('정지원', 'user3@test.com', '{noop}user1234#', '포토그래퍼', 'profile4.jpg', 4, 5, 'GOOGLE', 'USER', 1),
    ('강동욱', 'user4@test.com', '{noop}user1234$', '미식가', 'profile5.jpg', 5, 6, 'KAKAO', 'USER', 1),
    ('홍길동', 'user5@test.com', '{noop}user1234%', '국내여행러', null, 6, 7, 'LOCAL', 'USER', 1),
    ('송민주', 'user6@test.com', '{noop}user1234^', '여행블로거', 'profile7.jpg', 7, 8, 'NAVER', 'USER', 1),
    ('임수진', 'user7@test.com', '{noop}user1234&', '맛집탐방러', 'profile8.jpg', 8, null, 'LOCAL', 'USER', 1),
    ('오태호', 'user8@test.com', '{noop}user1234*', '등산매니아', 'profile9.jpg', 9, 9, 'LOCAL', 'USER', 1),
    ('최예린', 'user9@test.com', '{noop}user1234()', '카페탐방러', 'profile10.jpg', 10, 10, 'LOCAL', 'USER', 1);                                                                                                                                                        ('최예린', 'user9@test.com', '{noop}user1234()', '카페탐방러', 'profile10.jpg', 10, 10, 'LOCAL', 'USER', 1);                                                                                                                                                       ('최예린', 'user9@test.com', '$2a$10$dPbXkIF9BXfncxyeKoOkXO5P5P5P5P5P5P5P5P5P5P5P5P5P5P5P5', '카페탐방러', 'profile10.jpg', 10, 10, 'LOCAL', 'USER', 1);  -- 비밀번호: user1234()

-- Reviews 데이터 (tour_id는 실제 tour 테이블의 id와 맞춰야 합니다)
INSERT INTO reviews (review_title, review_content, tour_id, member_id, review_rating, created_at, updated_at) VALUES
                                                                                                                  ('최고의 여행지!', '정말 좋은 경험이었습니다.', 1, 1, 5, NOW(), NOW()),
                                                                                                                  ('추천합니다', '가족과 함께 가기 좋아요', 2, 2, 4, NOW(), NOW()),
                                                                                                                  ('다시 방문하고 싶어요', '뷰가 정말 끝내줍니다.', 3, 3, 5, NOW(), NOW()),
                                                                                                                  ('조금 아쉬워요', '주차가 불편했습니다.', 4, 4, 3, NOW(), NOW()),
                                                                                                                  ('맛집 추천', '주변 맛집이 정말 많아요!', 5, 5, 4, NOW(), NOW()),
                                                                                                                  ('또 가고 싶어요', '날씨도 좋고 최고였어요', 1, 6, 5, NOW(), NOW()),
                                                                                                                  ('좋은 추억 만들었어요', '친구들과 함께 가기 좋아요', 2, 7, 4, NOW(), NOW()),
                                                                                                                  ('괜찮았어요', '전반적으로 만족합니다.', 3, 8, 4, NOW(), NOW()),
                                                                                                                  ('최고의 힐링', '조용히 쉬기 좋았어요', 4, 9, 5, NOW(), NOW()),
                                                                                                                  ('아이들이 좋아해요', '아이들과 함께 가기 좋습니다', 5, 10, 4, NOW(), NOW());

-- Review Likes 데이터
INSERT INTO review_likes (member_id, review_id) VALUES
                                                    (1, 2), (1, 3), (1, 4),
                                                    (2, 1), (2, 3), (2, 5),
                                                    (3, 1), (3, 2), (3, 4),
                                                    (4, 5), (4, 6), (4, 7),
                                                    (5, 8), (5, 9), (5, 10);

-- Review Comments 데이터
INSERT INTO review_comments (review_id, member_id, content) VALUES
                                                                (1, 2, '저도 다녀왔는데 정말 좋았어요!'),
                                                                (1, 3, '사진이 너무 예쁘네요'),
                                                                (2, 1, '추천해주셔서 감사합니다'),
                                                                (3, 4, '주변 맛집 추천해주세요'),
                                                                (4, 5, '주차장 위치 알려주실 수 있나요?'),
                                                                (5, 1, '다음에 꼭 가보겠습니다'),
                                                                (6, 2, '위치가 어디인가요?'),
                                                                (7, 3, '유용한 정보 감사합니다'),
                                                                (8, 4, '혹시 입장료는 얼마인가요?'),
                                                                (9, 5, '주말에도 사람이 많나요?');

-- Review Images 데이터
INSERT INTO review_images (image, review_id) VALUES
                                                 ('review1_1.jpg', 1), ('review1_2.jpg', 1),
                                                 ('review2_1.jpg', 2), ('review2_2.jpg', 2),
                                                 ('review3_1.jpg', 3),
                                                 ('review4_1.jpg', 4), ('review4_2.jpg', 4),
                                                 ('review5_1.jpg', 5),
                                                 ('review6_1.jpg', 6),
                                                 ('review7_1.jpg', 7);

-- Notice 데이터
INSERT INTO notice (notice_title, notice_content) VALUES
                                                      ('사이트 점검 안내', '2024년 1월 1일 새벽 2시부터 4시까지 점검이 있을 예정입니다.'),
                                                      ('새로운 기능 업데이트', '리뷰 작성 시 이미지 여러 장 업로드가 가능해졌습니다.'),
                                                      ('이벤트 안내', '신년 맞이 포토 후기 이벤트를 진행합니다.'),
                                                      ('약관 개정 안내', '개인정보처리방침이 개정될 예정입니다.'),
                                                      ('휴면 계정 안내', '1년 이상 미접속 계정은 휴면 처리됩니다.'),
                                                      ('베스트 리뷰어 발표', '12월의 베스트 리뷰어가 발표되었습니다.'),
                                                      ('겨울 여행 특집', '겨울 여행지 추천 게시글이 업데이트 되었습니다.'),
                                                      ('앱 출시 안내', '모바일 앱이 출시되었습니다.'),
                                                      ('포인트 제도 개편', '포인트 적립 정책이 변경됩니다.'),
                                                      ('등급 혜택 안내', '회원 등급별 혜택이 업데이트 되었습니다.');

-- Chat Bot 데이터
INSERT INTO chat_bot (member_id, user_request, bot_response) VALUES
                                                                 (1, '근처 맛집 추천해줘', '현재 위치 주변의 인기 맛집을 검색해드리겠습니다.'),
                                                                 (2, '날씨 어때?', '오늘은 맑은 날씨가 예상됩니다.'),
                                                                 (3, '관광지 추천', '주변의 인기 관광지를 추천해드립니다.'),
                                                                 (4, '숙소 찾아줘', '원하시는 지역의 숙소를 검색해드리겠습니다.'),
                                                                 (5, '버스 시간표', '버스 시간표를 검색해드리겠습니다.'),
                                                                 (1, '미술관 위치', '가까운 미술관의 위치를 안내해드리겠습니다.'),
                                                                 (2, '주차장 위치', '주변 주차장 정보를 검색해드리겠습니다.'),
                                                                 (3, '축제 정보', '이번 달 예정된 축제 정보를 알려드리겠습니다.'),
                                                                 (4, '자전거 대여', '자전거 대여소 위치를 안내해드리겠습니다.'),
                                                                 (5, '화장실 위치', '가까운 공공 화장실 위치를 안내해드리겠습니다.');