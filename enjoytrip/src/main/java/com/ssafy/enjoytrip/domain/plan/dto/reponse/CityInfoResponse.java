package com.ssafy.enjoytrip.domain.plan.dto.reponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CityInfoResponse {
    private Long cityCode;
    private String cityName;

    @Builder
    public CityInfoResponse(Long cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }
}
