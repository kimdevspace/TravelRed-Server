package com.ssafy.enjoytrip.domain.review.controller;

import com.ssafy.enjoytrip.domain.review.dto.request.CreateReviewRequestDto;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import com.ssafy.enjoytrip.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trip/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody CreateReviewRequestDto reviewRequestDto) {
        CreateReviewRequestDto result = reviewService.saveReview(reviewRequestDto);
        return ResponseEntity.ok(result);
    }



}
