package com.ssafy.enjoytrip.domain.review.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SearchReviewResponseDto {
    private Long reviewId;
    private String reviewTitle;
    private String reviewContent;
    private String reviewImage;
    private Integer likeCount;    // Long -> Integer로 수정
    private Integer rating;
    private LocalDateTime updateAt;
    private Long memberId;
    private String profileImage;
    private String nickname;

    public SearchReviewResponseDto(
            Long reviewId,
            String reviewTitle,
            String reviewContent,
            String reviewImage,
            Integer rating,
            Integer likeCount,    // Long -> Integer로 수정
            LocalDateTime updateAt,
            Long memberId,
            String profileImage,
            String nickname) {
        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewImage = reviewImage;
        this.rating = rating;
        this.likeCount = likeCount;
        this.updateAt = updateAt;
        this.memberId = memberId;
        this.profileImage = profileImage;
        this.nickname = nickname;
    }
}