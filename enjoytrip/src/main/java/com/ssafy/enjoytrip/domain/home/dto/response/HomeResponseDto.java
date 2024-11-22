package com.ssafy.enjoytrip.domain.home.dto.response;

import com.ssafy.enjoytrip.domain.notice.entity.Notice;
import com.ssafy.enjoytrip.domain.review.dto.response.HomeReviewResponseDto;
import com.ssafy.enjoytrip.domain.tour.dto.response.HomeTourResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponseDto {
    private List<HomeTourResponseDto> tours;
    private List<HomeReviewResponseDto> reviews;
    private List<Notice> notices;
}
