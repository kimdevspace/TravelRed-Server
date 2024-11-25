package com.ssafy.enjoytrip.domain.admin.reviews.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewAdminResponseDto {
    private Long reviewId;
    private String memberName;
    private String content;
    private LocalDateTime createdAt;
}
