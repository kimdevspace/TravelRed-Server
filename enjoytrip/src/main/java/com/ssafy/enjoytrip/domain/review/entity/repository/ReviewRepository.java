package com.ssafy.enjoytrip.domain.review.entity.repository;

import com.ssafy.enjoytrip.domain.review.dto.response.HomeReviewResponseDto;
import com.ssafy.enjoytrip.domain.review.dto.response.SearchReviewResponseDto;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
            "r.member.id," +
            "r.member.nickname," +
            "r.member.profileImage," +
            "COALESCE(r.likeCount, 0), " +
            "COALESCE(r.rating, 0)) " +
            "FROM Review r " +
            "ORDER BY r.rating DESC NULLS LAST, r.likeCount DESC NULLS LAST")
    List<HomeReviewResponseDto> findTopReviewsOrderByRating(Pageable pageable);

    @Query("SELECT r.likeCount FROM Review r WHERE r.reviewId = :reviewId")
    Integer findLikeCountByReviewId(@Param("reviewId") Long reviewId);

    void deleteAllByReviewIdIn(List<Long> ids);

    @Query("SELECT new com.ssafy.enjoytrip.domain.review.dto.response.SearchReviewResponseDto(" +
            "r.reviewId, " +           // 1
            "r.reviewTitle, " +        // 2
            "r.reviewContent, " +      // 3
            "r.reviewImage, " +        // 4
            "r.rating, " +            // 5
            "r.likeCount, " +         // 6
            "r.updatedAt, " +         // 7
            "m.id, " +                // 8
            "m.profileImage, " +      // 9
            "m.nickname) " +          // 10
            "FROM Review r " +
            "JOIN r.member m " +
            "ORDER BY r.updatedAt DESC")
    List<SearchReviewResponseDto> findAllReviewsWithMamberInfo();

    Long countByMemberId(Long memberId);
    List<Review> findByMemberId(Long memberId);


    @Query("SELECT r FROM Review r " +
            "JOIN ReviewLike rl ON r.id = rl.review.id " +
            "WHERE rl.member.id = :memberId")
    List<Review> findLikedReviewsByMemberId(@Param("memberId") Long memberId);

}
