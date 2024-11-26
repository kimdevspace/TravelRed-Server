package com.ssafy.enjoytrip.domain.review.dto.response;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.city.entity.Town;
import com.ssafy.enjoytrip.domain.tour.entity.TourContent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SearchReviewResponseDto {

    private Long reviewId;          // Review의 id
    private String reviewTitle;     // Review의 title
    private String reviewContent;   // Review의 content
    private String reviewImage;     // Review의 reviewImage
    private Integer rating;         // Review의 rating
    private LocalDateTime updateAt; // Review의 updateAt

    // 멤버 정보
    private Long memberId;         // Member의 id
    private String profileImage;   // Member의 profileImage
    private String nickname;       // Member의 nickname

    public SearchReviewResponseDto(
            Long reviewId,
            String reviewTitle,
            String reviewContent,
            String reviewImage,
            Integer rating,
            LocalDateTime updateAt,
            Long memberId,
            String profileImage,
            String nickname) {
        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewImage = reviewImage;
        this.rating = rating;
        this.updateAt = updateAt;
        this.memberId = memberId;
        this.profileImage = profileImage;
        this.nickname = nickname;
    }
}