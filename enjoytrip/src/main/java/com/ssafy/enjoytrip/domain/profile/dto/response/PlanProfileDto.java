package com.ssafy.enjoytrip.domain.profile.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PlanProfileDto {
    private Long id;
    private String title;
    private String thumbnailImage;
    private LocalDate startDate;
    private LocalDate endDate;
    private int day;
    private String cityName;
}