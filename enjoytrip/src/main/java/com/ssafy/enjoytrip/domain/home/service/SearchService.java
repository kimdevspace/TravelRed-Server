package com.ssafy.enjoytrip.domain.home.service;

import com.ssafy.enjoytrip.domain.home.dto.response.HomeResponseDto;
import com.ssafy.enjoytrip.domain.home.dto.response.SearchResponseDto;
import com.ssafy.enjoytrip.domain.notice.service.NoticeService;
import com.ssafy.enjoytrip.domain.plan.service.PlanService;
import com.ssafy.enjoytrip.domain.review.service.ReviewService;
import com.ssafy.enjoytrip.domain.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {

    private final PlanService planService;
    private final ReviewService reviewService;
    private final NoticeService noticeService;

    public SearchResponseDto makeSearchPage() {
        return SearchResponseDto.builder()
                .plans(planService.getPlanForSearchPage())
                .reviews(reviewService.getReivewForSearchPage())
                .notices(noticeService.getAll())
                .build();
    }

}