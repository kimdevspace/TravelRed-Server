package com.ssafy.enjoytrip.domain.admin.notices.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeDetailResponseDto {
    private Long id;
    private String title;
    private String memberName;
    private LocalDateTime createdAt;
    private String content;
}
