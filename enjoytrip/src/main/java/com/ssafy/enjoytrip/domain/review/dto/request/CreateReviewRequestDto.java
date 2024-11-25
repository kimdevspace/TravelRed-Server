package com.ssafy.enjoytrip.domain.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequestDto {

    private String reviewTitle;
    private String reviewContent;
    private Long tourId;
    private Long memberId;
    private String memberName;
    private Integer rating;
    private String reviewImage;

}
