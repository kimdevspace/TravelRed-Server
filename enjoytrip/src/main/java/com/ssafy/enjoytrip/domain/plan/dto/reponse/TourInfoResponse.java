package com.ssafy.enjoytrip.domain.plan.dto.reponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourInfoResponse {
    private Long tourId;
    private String tourName;
    private String address;
    private String backgroundImage;
    private Integer avgRating;
    private Long reviewCount;

    @Builder
    public TourInfoResponse(Long tourId, String tourName, String address, String backgroundImage, Double avgRating, Long reviewCount) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.address = address;
        this.backgroundImage = backgroundImage;
        this.avgRating = avgRating != null ? avgRating.intValue() : 0;
        this.reviewCount = reviewCount;
    }
}
