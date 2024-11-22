package com.ssafy.enjoytrip.domain.review.dto.response;

import com.ssafy.enjoytrip.domain.member.dto.response.HomeMemberResponseDto;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HomeReviewResponseDto {

    private Long reviewId;

    private String reviewTitle;

    private String reviewContent;

    private Long tourId;

    private Integer likeCount;

    private Integer reviewRating;  // 1~5 사이의 정수값

    // JPQL에서 사용할 생성자
    public HomeReviewResponseDto(Long reviewId, String reviewTitle, String reviewContent,
                                 Long tourId, Integer likeCount, Integer reviewRating) {
        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.tourId = tourId;
        this.likeCount = likeCount;
        this.reviewRating = reviewRating;
    }

}
