package com.ssafy.enjoytrip.domain.review.entity.repository;

import com.ssafy.enjoytrip.domain.review.dto.response.HomeReviewResponseDto;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT new com.ssafy.enjoytrip.domain.review.dto.response.HomeReviewResponseDto(" +
            "r.reviewId, r.reviewTitle, r.reviewContent, r.tourId, " +
            "r.likeCount, r.reviewRating) " +
            "FROM Review r " +
            "WHERE r.reviewRating IS NOT NULL " +
            "ORDER BY r.reviewRating DESC, r.likeCount DESC")
    List<HomeReviewResponseDto> findTopReviewsOrderByRating(Pageable pageable);

}
