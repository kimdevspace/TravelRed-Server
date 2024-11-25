package com.ssafy.enjoytrip.domain.review.controller;

import com.ssafy.enjoytrip.domain.review.dto.request.CreateReviewRequestDto;
import com.ssafy.enjoytrip.domain.review.dto.request.UpdateReviewRequestDto;
import com.ssafy.enjoytrip.domain.review.dto.response.CreateReviewResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.ReviewDetailResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.ReviewResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.ReviewSummaryDto;
import com.ssafy.enjoytrip.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trip/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody CreateReviewRequestDto reviewRequestDto) {
        CreateReviewResponseDto result = reviewService.saveReview(reviewRequestDto);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewRequestDto updateRequestDto) {
        CreateReviewResponseDto result = reviewService.updateReview(reviewId, updateRequestDto);
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

}
