package com.ssafy.enjoytrip.domain.plan.service;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.city.entity.repository.CityRepository;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.plan.dto.response.CityInfoResponseDto;
import com.ssafy.enjoytrip.domain.plan.dto.response.SearchPlanResponseDto;
import com.ssafy.enjoytrip.domain.plan.dto.response.TourInfoResponseDto;
import com.ssafy.enjoytrip.domain.plan.dto.response.TripPlanInfoResponseDto;
import com.ssafy.enjoytrip.domain.plan.dto.request.CreatePlanRequestDto;
import com.ssafy.enjoytrip.domain.plan.entity.Plan;
import com.ssafy.enjoytrip.domain.plan.entity.PlanTrip;
import com.ssafy.enjoytrip.domain.plan.entity.repository.PlanRepository;
import com.ssafy.enjoytrip.domain.plan.entity.repository.PlanTripRepository;
import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import com.ssafy.enjoytrip.domain.tour.entity.TourDetail;
import com.ssafy.enjoytrip.domain.tour.entity.repository.TourRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final PlanTripRepository planTripRepository;
    private final TourRepository tourRepository;
    private final CityRepository cityRepository;

    public Long createPlan(@AuthenticationPrincipal Member member, CreatePlanRequestDto request) {
        City city = cityRepository.findById(request.getCityCode())
                .orElseThrow(() -> new EntityNotFoundException("도시를 찾을 수 없습니다"));

        Plan plan = Plan.builder()
                .title(request.getTitle())
                .member(member)
                .thumbnailImage(request.getThumbnailImage())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .city(city)
                .day(request.getDayPlans().size())
                .build();

        Plan savedPlan = planRepository.save(plan);

        List<PlanTrip> planTrips = request.getDayPlans().stream()
                .flatMap(dayPlanRequest -> dayPlanRequest.getTourIds().stream()
                        .map(tourOrder -> PlanTrip.builder()
                                .plan(savedPlan)
                                .tour(tourRepository.findById(tourOrder.getTourId())
                                        .orElseThrow(() -> new EntityNotFoundException("관광지를 찾을 수 없습니다")))
                                .day(dayPlanRequest.getDay())
                                .order(tourOrder.getOrder())
                                .build()))
                .toList();

        planTripRepository.saveAll(planTrips);

        return savedPlan.getId();
    }

    @Transactional(readOnly = true)
    public List<CityInfoResponseDto> getCitiesForPlanning() {
        return cityRepository.findAll().stream()
                .map(city -> CityInfoResponseDto.builder()
                        .cityCode(city.getId())
                        .cityName(city.getCityName())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TourInfoResponseDto> getToursByCity(Integer cityCode) {
        City city = cityRepository.findById(cityCode)
                .orElseThrow(() -> new EntityNotFoundException("도시를 찾을 수 없습니다"));
        return tourRepository.findTourInfoByCity(city);
    }

    @Transactional(readOnly = true)
    public TripPlanInfoResponseDto getPlanDetail(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("여행 계획을 찾을 수 없습니다."));

        List<PlanTrip> planTrips = planTripRepository.findByPlanOrderByDayAscOrderAsc(plan);

        Map<Integer, List<PlanTrip>> tripsByDay = planTrips.stream()
                .collect(Collectors.groupingBy(PlanTrip::getDay));

        List<TripPlanInfoResponseDto.DayPlanResponseDto> dayPlans = new ArrayList<>();

        for (int day = 1; day <= plan.getDay(); day++) {
            List<PlanTrip> dayTrips = tripsByDay.getOrDefault(day, new ArrayList<>());

            List<TripPlanInfoResponseDto.TourOrderResponseDto> tourOrders = dayTrips.stream()
                    .map(planTrip -> {
                        Tour tour = planTrip.getTour();
                        TourDetail tourDetail = tour.getTourDetail();

                        TripPlanInfoResponseDto.TourOrderResponseDto tourOrder = new TripPlanInfoResponseDto.TourOrderResponseDto();
                        tourOrder.setTourId(tour.getId());
                        tourOrder.setOrder(planTrip.getOrder());
                        tourOrder.setTourName(tour.getTourName());
                        tourOrder.setAddress(tour.getAddress());

                        if (tourDetail != null) {
                            tourOrder.setLatitude(tourDetail.getLatitude());
                            tourOrder.setLongitude(tourDetail.getLongitude());
                        }

                        return tourOrder;
                    })
                    .collect(Collectors.toList());

            TripPlanInfoResponseDto.DayPlanResponseDto dayPlan =
                    new TripPlanInfoResponseDto.DayPlanResponseDto();
            dayPlan.setDay(day);
            dayPlan.setTourIds(tourOrders);

            dayPlans.add(dayPlan);
        }

        TripPlanInfoResponseDto response = new TripPlanInfoResponseDto();
        response.setTitle(plan.getTitle());
        response.setStartDate(plan.getStartDate());
        response.setEndDate(plan.getEndDate());
        response.setCityCode(plan.getCity().getId());
        response.setDayPlans(dayPlans);

        return response;
    }

    public void deletePlan(Long planId, Member member) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 여행 계획입니다."));

        if (!plan.getMember().getId().equals(member.getId())) {
            throw new IllegalStateException("삭제 권한이 없습니다.");
        }

        planRepository.deleteById(planId);
    }

    public List<SearchPlanResponseDto> getPlanForSearchPage() {
        return planRepository.findAllPlansWithMemberInfo();
    }
}
