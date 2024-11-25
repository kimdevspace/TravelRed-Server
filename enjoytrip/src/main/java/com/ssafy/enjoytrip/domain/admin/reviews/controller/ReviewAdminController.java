package com.ssafy.enjoytrip.domain.admin.reviews.controller;

import com.ssafy.enjoytrip.domain.admin.reviews.dto.response.ReviewAdminResponseDto;
import com.ssafy.enjoytrip.domain.admin.reviews.service.ReviewAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/reviews")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ReviewAdminController {

    private final ReviewAdminService reviewAdminService;

    //모든 리뷰 조회하기
    @GetMapping
    public ResponseEntity<Page<ReviewAdminResponseDto>> getAllReviews(Pageable pageable) {
        return ResponseEntity.ok(reviewAdminService.getAllReviews(pageable));
    }
}
