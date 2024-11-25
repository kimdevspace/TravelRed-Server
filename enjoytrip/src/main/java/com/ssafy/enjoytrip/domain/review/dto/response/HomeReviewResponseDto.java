package com.ssafy.enjoytrip.domain.review.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HomeReviewResponseDto {

    private Long reviewId;
    private String reviewTitle;
    private String reviewContent;
    private Long tourId;
    private String tourName;
    private Integer likeCount;
    private Integer rating;

    // JPQL에서 사용할 생성자
    public HomeReviewResponseDto(Long reviewId, String reviewTitle, String reviewContent,
                                 Long tourId, String tourName, Integer likeCount, Integer rating) {
        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.tourId = tourId;
        this.tourName = tourName;
        this.likeCount = likeCount;
        this.rating = rating;
    }
}
