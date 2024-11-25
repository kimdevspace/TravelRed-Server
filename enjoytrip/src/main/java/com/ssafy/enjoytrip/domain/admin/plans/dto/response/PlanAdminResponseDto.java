package com.ssafy.enjoytrip.domain.admin.plans.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanAdminResponseDto {
    private Long planId;
    private String memberName;
    private String title;
    private Integer totalDays;
}
