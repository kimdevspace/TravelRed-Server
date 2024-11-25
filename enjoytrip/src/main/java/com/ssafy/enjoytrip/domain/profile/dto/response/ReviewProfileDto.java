package com.ssafy.enjoytrip.domain.profile.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewProfileDto {
    private Long id;
    private String title;
    private String content;
    private String reviewImage;
    private int likeCount;
    private int rating;
    private LocalDateTime createdAt;
}
