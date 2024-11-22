package com.ssafy.enjoytrip.domain.home.service;

import com.ssafy.enjoytrip.domain.home.dto.response.HomeResponseDto;
import com.ssafy.enjoytrip.domain.notice.service.NoticeService;
import com.ssafy.enjoytrip.domain.review.service.ReviewService;
import com.ssafy.enjoytrip.domain.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeService {

    private final TourService tourService;
    private final ReviewService reviewService;
    private final NoticeService noticeService;

    public HomeResponseDto makeHomePage() {
        return HomeResponseDto.builder()
                .tours(tourService.getTopHitTours())
                .reviews(reviewService.getTopRatedReviews())
                .notices(noticeService.getNoticesForHome())
                .build();
    }

}
