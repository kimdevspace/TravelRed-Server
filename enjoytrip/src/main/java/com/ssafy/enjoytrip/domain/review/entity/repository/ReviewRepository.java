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
            "r.reviewId, " +
            "r.reviewTitle, " +
            "r.reviewContent, " +
            "r.tour.id, " +        // Tour 엔티티의 id 필드
            "r.tour.tourName, " +  // Tour 엔티티의 tourName 필드
            "COALESCE(r.likeCount, 0), " +
            "COALESCE(r.rating, 0)) " +
            "FROM Review r " +
            "ORDER BY r.rating DESC NULLS LAST, r.likeCount DESC NULLS LAST")
    List<HomeReviewResponseDto> findTopReviewsOrderByRating(Pageable pageable);


}
