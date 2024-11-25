package com.ssafy.enjoytrip.domain.plan.service;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.city.entity.repository.CityRepository;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.plan.dto.reponse.CityInfoResponseDto;
import com.ssafy.enjoytrip.domain.plan.dto.reponse.TourInfoResponseDto;
import com.ssafy.enjoytrip.domain.plan.dto.request.CreatePlanRequestDto;
import com.ssafy.enjoytrip.domain.plan.entity.Plan;
import com.ssafy.enjoytrip.domain.plan.entity.PlanTrip;
import com.ssafy.enjoytrip.domain.plan.entity.repository.PlanRepository;
import com.ssafy.enjoytrip.domain.plan.entity.repository.PlanTripRepository;
import com.ssafy.enjoytrip.domain.tour.entity.repository.TourRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
