package com.ssafy.enjoytrip.domain.review.service;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import com.ssafy.enjoytrip.domain.review.dto.request.ReviewLikeRequestDto;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import com.ssafy.enjoytrip.domain.review.entity.ReviewLike;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewLikeRepository;
import com.ssafy.enjoytrip.domain.review.entity.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional  // 트랜잭션 추가
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public String toggleLike(ReviewLikeRequestDto requestDto) {
        Review review = reviewRepository.findById(requestDto.getReviewId())
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Optional<ReviewLike> existingLike = reviewLikeRepository.findByReviewAndMember(review, member);

        if (existingLike.isPresent()) {
            // 좋아요가 이미 있으면 삭제
            reviewLikeRepository.delete(existingLike.get());

            // 리뷰의 좋아요 수 감소
            review.setLikeCount(review.getLikeCount() - 1);
            reviewRepository.save(review);

            return "좋아요가 취소되었습니다.";
        }

        // 좋아요가 없으면 새로 생성
        ReviewLike reviewLike = new ReviewLike(review, member);
        reviewLikeRepository.save(reviewLike);

        // 리뷰의 좋아요 수 증가
        review.setLikeCount(review.getLikeCount() != null ? review.getLikeCount() + 1 : 1);
        reviewRepository.save(review);

        return "좋아요가 추가되었습니다.";
    }
}