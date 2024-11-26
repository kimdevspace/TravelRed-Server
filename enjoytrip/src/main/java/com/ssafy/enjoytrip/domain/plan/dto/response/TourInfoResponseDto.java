package com.ssafy.enjoytrip.domain.plan.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourInfoResponseDto {
    private Long tourId;
    private String tourName;
    private String address;
    private String backgroundImage;
    private Integer avgRating;
    private Long reviewCount;

    // 위도 경도 추가
    private BigDecimal latitude;
    private BigDecimal longitude;


    @Builder
    public TourInfoResponseDto(Long tourId, String tourName, String address, String backgroundImage, BigDecimal latitude, BigDecimal longitude, Double avgRating, Long reviewCount) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.address = address;
        this.backgroundImage = backgroundImage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.avgRating = avgRating != null ? avgRating.intValue() : 0;
        this.reviewCount = reviewCount;
    }
}
