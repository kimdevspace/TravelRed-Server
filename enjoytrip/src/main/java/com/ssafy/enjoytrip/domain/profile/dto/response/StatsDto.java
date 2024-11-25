package com.ssafy.enjoytrip.domain.profile.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatsDto {
    private Long reviewCount;
    private Long planCount;
}