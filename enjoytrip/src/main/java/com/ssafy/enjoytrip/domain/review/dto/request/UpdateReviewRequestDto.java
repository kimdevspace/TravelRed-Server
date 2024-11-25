package com.ssafy.enjoytrip.domain.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewRequestDto {
    private Long reviewId;  // 수정할 리뷰의 ID
    private String reviewTitle;
    private String reviewContent;
    private Integer rating;
    private String reviewImage;
    private Long tourId;
    private Long memberId;
    private String memberName;
}
