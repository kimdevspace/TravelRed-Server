package com.ssafy.enjoytrip.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EachTourReviewResponseDto {

    // 관광지의 이미지, 주소, 우편번호
    private String backgroundImage;
    private String address;
    private String zipCode;
    
    private Long tourId;
    private String description;
    private List<ReviewSummaryDto> reviews;

}
