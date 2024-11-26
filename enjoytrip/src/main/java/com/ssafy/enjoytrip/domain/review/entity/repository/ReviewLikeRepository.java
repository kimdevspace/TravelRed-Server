package com.ssafy.enjoytrip.domain.review.entity.repository;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import com.ssafy.enjoytrip.domain.review.entity.ReviewLike;
import com.ssafy.enjoytrip.domain.review.entity.ReviewLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, ReviewLikeId> {

    @Modifying
    @Query("DELETE FROM ReviewLike rl WHERE rl.review.reviewId = :reviewId")
    void deleteByReviewId(@Param("reviewId") Long reviewId);

    Optional<ReviewLike> findByReviewAndMember(Review review, Member member);
}