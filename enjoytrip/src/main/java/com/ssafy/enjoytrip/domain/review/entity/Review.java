package com.ssafy.enjoytrip.domain.review.entity;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @NotNull
    private String reviewTitle;

    @Column(name = "review_content", nullable = false, columnDefinition = "text")
    private String reviewContent;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @NotNull
    private String reviewImage;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createdAt;

    private Integer likeCount;

    private Integer rating;  // 1~5 사이의 정수값

    @NotNull
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void validateRating() {
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }

    @Builder
    public Review(Long reviewId, String reviewTitle, String reviewContent, Member member, Tour tour, String reviewImage, Integer likeCount, Integer rating) {
        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.member = member;
        this.tour = tour;
        this.reviewImage = reviewImage;
        this.likeCount = 0;
        this.rating = rating;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }
}
