package com.ssafy.enjoytrip.domain.profile.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResponseDto {
    private UserInfoDto userInfo;
    private StatsDto stats;
}