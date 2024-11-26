package com.ssafy.enjoytrip.domain.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewCommentRequestDto {

    private Long reviewId;
    private Long memberId; // 작성자 Id
    private String content;

}
