package com.ssafy.enjoytrip.domain.review.entity;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "review_like")
@IdClass(ReviewLikeId.class)
public class ReviewLike {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public ReviewLike(Review review, Member member) {
        this.review = review;
        this.member = member;
    }
}