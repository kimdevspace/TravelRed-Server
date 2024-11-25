package com.ssafy.enjoytrip.domain.profile.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProfileReviewCommentDto {
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private ReviewInfo review;

    @Getter
    @Builder
    public static class ReviewInfo {
        private Long reviewId;
        private String title;
    }
}