package com.ssafy.enjoytrip.domain.review.controller;

import com.ssafy.enjoytrip.domain.review.dto.request.CreateReviewRequestDto;
import com.ssafy.enjoytrip.domain.review.dto.request.ReviewLikeRequestDto;
import com.ssafy.enjoytrip.domain.review.dto.request.UpdateReviewRequestDto;
import com.ssafy.enjoytrip.domain.review.dto.response.CreateReviewResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.ReviewDetailResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.ReviewResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.ReviewSummaryDto;
import com.ssafy.enjoytrip.domain.review.service.ReviewLikeService;
import com.ssafy.enjoytrip.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trip/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewLikeService reviewLikeService;

    @PostMapping("/like")
    public ResponseEntity<?> toggleLike(@RequestBody ReviewLikeRequestDto requestDto) {
        String result = reviewLikeService.toggleLike(requestDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody CreateReviewRequestDto reviewRequestDto) {
        CreateReviewResponseDto result = reviewService.saveReview(reviewRequestDto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping
    public ResponseEntity<?> updateReview(@RequestBody UpdateReviewRequestDto updateRequestDto) {
        CreateReviewResponseDto result = reviewService.updateReview(updateRequestDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> verifyToken() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{tourId}")
    public ResponseEntity<?> getReview(@PathVariable Long tourId) {
        ReviewResponseDto reviewResponseDto = reviewService.getReview(tourId);
        return ResponseEntity.ok(reviewResponseDto);
    }

    @GetMapping("/detail/{reviewId}")
    public ResponseEntity<?> getReviewDetail(@PathVariable Long reviewId) {
        ReviewDetailResponseDto reviewDetailResponseDto = reviewService.getReviewDetail(reviewId);
        return ResponseEntity.ok(reviewDetailResponseDto);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();  // 204 No Content 반환
    }



}
