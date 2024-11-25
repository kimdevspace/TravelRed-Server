package com.ssafy.enjoytrip.domain.review.entity.repository;

import com.ssafy.enjoytrip.domain.review.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
    @Query("SELECT COUNT(rc) FROM ReviewComment rc WHERE rc.review.reviewId = :reviewId")
    Long countByReviewIdWithQuery(@Param("reviewId") Long reviewId);
    @Query("SELECT rc FROM ReviewComment rc WHERE rc.review.reviewId = :reviewId")
    List<ReviewComment> findAllByReviewIdWithQuery(@Param("reviewId") Long reviewId);

    @Modifying
    @Query("DELETE FROM ReviewComment rc WHERE rc.review.reviewId = :reviewId")
    void deleteByReviewId(@Param("reviewId") Long reviewId);


    @Query("SELECT rc FROM ReviewComment rc " +
            "JOIN FETCH rc.review r " +
            "WHERE rc.member.id = :memberId")
    List<ReviewComment> findByMemberId(@Param("memberId") Long memberId);

}
