package com.ssafy.enjoytrip.domain.plan.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CityInfoResponseDto {
    private Long cityCode;
    private String cityName;

    @Builder
    public CityInfoResponseDto(Long cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }
}
