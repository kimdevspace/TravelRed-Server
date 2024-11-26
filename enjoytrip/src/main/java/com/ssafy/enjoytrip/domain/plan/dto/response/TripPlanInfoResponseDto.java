package com.ssafy.enjoytrip.domain.plan.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TripPlanInfoResponseDto {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long cityCode;
    private List<DayPlanResponseDto> dayPlans;

    @Getter @Setter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class DayPlanResponseDto {
        private Integer day;
        private List<TourOrderResponseDto> tourIds;
    }

    @Getter @Setter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class TourOrderResponseDto {
        private Long tourId;
        private Integer order;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private String tourName;
        private String address;
    }
}
