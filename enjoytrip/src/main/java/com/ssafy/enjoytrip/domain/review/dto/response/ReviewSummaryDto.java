package com.ssafy.enjoytrip.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSummaryDto {

//    private String description;
    private Long reviewId;
    private String nickname;
    private String profile_image;
    private Long memberId;
    private String images;
    private String reviewTitle;
    private String reviewContent;

}
