package com.ssafy.enjoytrip.domain.review.service;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import com.ssafy.enjoytrip.domain.review.dto.request.CreateReviewRequestDto;
import com.ssafy.enjoytrip.domain.review.dto.response.HomeReviewResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.ReviewResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.ReviewSummaryDto;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewRepository;
import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import com.ssafy.enjoytrip.domain.tour.entity.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final TourRepository tourRepository;
    private final MemberRepository memberRepository;

    public List<HomeReviewResponseDto> getTopRatedReviews() {
        return reviewRepository.findTopReviewsOrderByRating(PageRequest.of(0, 20));
    }

    public CreateReviewRequestDto saveReview(CreateReviewRequestDto reviewRequestDto) {
        Tour tour = tourRepository.findTourById(reviewRequestDto.getTourId());
        Member member = memberRepository.findMemberById(reviewRequestDto.getMemberId());

        Review review = Review.builder()
                .reviewTitle(reviewRequestDto.getReviewTitle())
                .reviewContent(reviewRequestDto.getReviewContent())
                .tour(tour)
                .member(member)
                .rating(reviewRequestDto.getRating())
                .reviewImage(reviewRequestDto.getReviewImage())
                .build();

        reviewRepository.save(review);

        return reviewRequestDto;

    }

    public ReviewResponseDto getReview(Long tourId) {
        Tour tour = tourRepository.findTourById(tourId);
        List<ReviewSummaryDto> list = new ArrayList<>();

        for (Review review : tour.getReviews()) {
            ReviewSummaryDto summaryDto = ReviewSummaryDto.builder()
                    .reviewId(review.getReviewId())
                    .nickname(review.getMember().getNickname())
                    .profile_image(review.getMember().getProfileImage())
                    .memberId(review.getMember().getId())
                    .images(review.getReviewImage())
                    .reviewTitle(review.getReviewTitle())
                    .reviewContent(review.getReviewContent())
                    .build();
            list.add(summaryDto);
        }

        ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
                        .tourId(tourId)
                        .description(tour.getTourDetail().getDescription())
                        .reviews(list)
                        .build();

        return reviewResponseDto;
    }


}
