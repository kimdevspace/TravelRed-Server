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
public class ReviewDetailResponseDto {

    // 댓글 개수, 좋아요 수
    // 댓글 전체
    private Long commentCount;
    private Long likeCount;
    private List<ReviewCommentDto> comments;

}
