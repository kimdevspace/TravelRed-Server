package com.ssafy.enjoytrip.domain.plan.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor  // 기본 생성자 추가
public class SearchPlanResponseDto {

    private Long planId;
    private String title;
    private String thumbnailImage;
    private LocalDate startDate;    // LocalDateTime -> LocalDate로 변경
    private LocalDate endDate;      // LocalDateTime -> LocalDate로 변경
    private Long memberId;
    private String profileImage;
    private String nickname;

    // Builder 대신 일반 생성자 사용
    public SearchPlanResponseDto(
            Long planId,
            String title,
            String thumbnailImage,
            LocalDate startDate,
            LocalDate endDate,
            Long memberId,
            String profileImage,
            String nickname
    ) {
        this.planId = planId;
        this.title = title;
        this.thumbnailImage = thumbnailImage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberId = memberId;
        this.profileImage = profileImage;
        this.nickname = nickname;
    }
}
