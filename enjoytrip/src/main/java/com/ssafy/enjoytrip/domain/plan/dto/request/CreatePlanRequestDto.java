package com.ssafy.enjoytrip.domain.plan.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePlanRequestDto {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String thumbnailImage;
    private Integer cityCode;
    private List<DayPlanRequestDto> dayPlans;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DayPlanRequestDto {
        private Integer day;
        private List<TourOrderDto> tourIds;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static  class TourOrderDto {
        private Long tourId;
        private Integer order;
    }
}
