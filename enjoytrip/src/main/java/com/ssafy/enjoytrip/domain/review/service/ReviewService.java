package com.ssafy.enjoytrip.domain.review.service;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import com.ssafy.enjoytrip.domain.review.dto.request.CreateReviewRequestDto;
import com.ssafy.enjoytrip.domain.review.dto.request.UpdateReviewRequestDto;
import com.ssafy.enjoytrip.domain.review.dto.response.*;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import com.ssafy.enjoytrip.domain.review.entity.ReviewComment;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewCommentRepository;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewRepository;
import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import com.ssafy.enjoytrip.domain.tour.entity.repository.TourRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final ReviewCommentRepository reviewCommentRepository;

    public List<HomeReviewResponseDto> getTopRatedReviews() {
        return reviewRepository.findTopReviewsOrderByRating(PageRequest.of(0, 20));
    }

    public CreateReviewResponseDto saveReview(CreateReviewRequestDto reviewRequestDto) {
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

        return CreateReviewResponseDto.builder()
                .reviewId(review.getReviewId())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .rating(review.getRating())
                .reviewImage(review.getReviewImage())
                .updatedAt(review.getCreatedAt())
                .memberName(member.getMemberName())
                .memberId(member.getId())
                .tourId(tour.getId())
                .build();

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

    public ReviewDetailResponseDto getReviewDetail(Long reviewId) {

        List<ReviewCommentDto> comments = new ArrayList<>();
        List<ReviewComment> reviewComments = reviewCommentRepository.findAllByReviewIdWithQuery(reviewId);

        for (ReviewComment r : reviewComments) {
            comments.add(ReviewCommentDto.builder()
                    .commentId(r.getCommentId())
                    .content(r.getContent())
                    .memberId(r.getMember().getId())
                    .createAt(r.getCreatedAt())
                    .build());
        }

        ReviewDetailResponseDto detailResponseDto = ReviewDetailResponseDto.builder()
                .commentCount(reviewCommentRepository.countByReviewIdWithQuery(reviewId))
                .likeCount(reviewRepository.findLikeCountByReviewId(reviewId))
                .comments(comments)
                .build();

        return detailResponseDto;
    }

    public CreateReviewResponseDto updateReview(Long reviewId, UpdateReviewRequestDto updateRequestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + reviewId));

        // 리뷰 정보 업데이트
        if (updateRequestDto.getReviewTitle() != null) {
            review.updateReviewTitle(updateRequestDto.getReviewTitle());
        }
        if (updateRequestDto.getReviewContent() != null) {
            review.updateReviewContent(updateRequestDto.getReviewContent());
        }
        if (updateRequestDto.getRating() != null) {
            review.updateRating(updateRequestDto.getRating());
        }
        if (updateRequestDto.getReviewImage() != null) {
            review.updateReviewImage(updateRequestDto.getReviewImage());
        }

        Review updatedReview = reviewRepository.save(review);

        return CreateReviewResponseDto.builder()
                .reviewId(updatedReview.getReviewId())
                .reviewTitle(updatedReview.getReviewTitle())
                .reviewContent(updatedReview.getReviewContent())
                .rating(updatedReview.getRating())
                .reviewImage(updatedReview.getReviewImage())
                .updatedAt(updatedReview.getUpdatedAt())
                .memberName(updateRequestDto.getMemberName())
                .memberId(updateRequestDto.getMemberId())
                .tourId(updateRequestDto.getTourId())
                .build();
    }

}
