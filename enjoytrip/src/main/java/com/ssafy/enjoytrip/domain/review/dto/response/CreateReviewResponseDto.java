package com.ssafy.enjoytrip.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CreateReviewResponseDto {

    private Long reviewId;
    private String reviewTitle;
    private String reviewContent;
    private Integer rating;
    private String reviewImage;
    private LocalDateTime updatedAt;
    private Long tourId;
    private Long memberId;
    private String memberName;
}
