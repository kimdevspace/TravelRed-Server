package com.ssafy.enjoytrip.domain.profile.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoDto {
    private String memberName;
    private String email;
    private String nickname;
    private String profileImage;
    private String cityName;
    private String townName;
}
