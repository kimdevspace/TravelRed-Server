package com.ssafy.enjoytrip.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetRequestDto {
    private String email;
    private String token;      // 이메일로 전송된 임시 토큰
    private String newPassword;
}
