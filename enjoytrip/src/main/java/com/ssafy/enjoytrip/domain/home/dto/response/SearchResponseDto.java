package com.ssafy.enjoytrip.domain.home.dto.response;

import com.ssafy.enjoytrip.domain.notice.entity.Notice;
import com.ssafy.enjoytrip.domain.plan.dto.response.SearchPlanResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.SearchReviewResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponseDto {
    private List<SearchPlanResponseDto> plans;
    private List<SearchReviewResponseDto> reviews;
    private List<Notice> notices;
}