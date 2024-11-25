package com.ssafy.enjoytrip.domain.review.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

    private Long tourId;
    private String description;
    private List<ReviewSummaryDto> reviews;

}
