package com.ssafy.enjoytrip.domain.member.dto.response;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class HomeMemberResponseDto {
    private String nickname;
    private String profileImage;

    // JPQL에서 사용할 추가 생성자
    public HomeMemberResponseDto(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}