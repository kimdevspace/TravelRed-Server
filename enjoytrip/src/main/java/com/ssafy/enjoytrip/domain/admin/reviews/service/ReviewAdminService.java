package com.ssafy.enjoytrip.domain.admin.reviews.service;

import com.ssafy.enjoytrip.domain.admin.reviews.dto.response.ReviewAdminResponseDto;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewAdminService {

    private final ReviewRepository reviewRepository;

    public Page<ReviewAdminResponseDto> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(this::convertToAdminDto);
    }

    private ReviewAdminResponseDto convertToAdminDto(Review review) {
        return ReviewAdminResponseDto.builder()
                .reviewId(review.getReviewId())
                .memberName(review.getMember().getMemberName())
                .content(review.getReviewContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
