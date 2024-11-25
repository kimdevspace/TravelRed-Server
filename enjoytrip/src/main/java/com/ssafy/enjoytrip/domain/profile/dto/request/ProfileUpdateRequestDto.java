package com.ssafy.enjoytrip.domain.profile.dto.request;

import lombok.Getter;

@Getter
public class ProfileUpdateRequestDto {
    private String nickname;        // 닉네임
    private String profileImage;    // 프로필 이미지
    private Integer townCode;       // 시/군/구 코드
    private Integer cityCode;       // 시/도 코드
}
