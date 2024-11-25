package com.ssafy.enjoytrip.domain.review.entity.repository;

import com.ssafy.enjoytrip.domain.review.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
    Long countByReview_ReviewId(Long reviewId);
    List<ReviewComment> findAllByReview_ReviewId(Long reviewId);
}
