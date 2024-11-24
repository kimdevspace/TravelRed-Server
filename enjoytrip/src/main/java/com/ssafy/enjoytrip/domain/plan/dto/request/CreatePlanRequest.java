package com.ssafy.enjoytrip.domain.plan.dto.request;

import com.ssafy.enjoytrip.domain.city.entity.City;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePlanRequest {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String thumbnailImage;
    private Integer cityCode;
    private List<DayPlanRequest> dayPlans;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DayPlanRequest {
        private Integer day;
        private List<TourOrder> tourIds;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static  class TourOrder {
        private Long tourId;
        private Integer order;
    }
}
