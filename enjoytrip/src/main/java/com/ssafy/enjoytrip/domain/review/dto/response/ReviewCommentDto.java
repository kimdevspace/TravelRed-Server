package com.ssafy.enjoytrip.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCommentDto {

    private Long memberId;
    private String profileImage;
    private Long commentId;
    private String content;
    private LocalDateTime createAt;

}
