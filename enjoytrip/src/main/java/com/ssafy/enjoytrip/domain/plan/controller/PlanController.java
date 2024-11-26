package com.ssafy.enjoytrip.domain.plan.controller;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.plan.dto.response.CityInfoResponseDto;
import com.ssafy.enjoytrip.domain.plan.dto.response.TourInfoResponseDto;
import com.ssafy.enjoytrip.domain.plan.dto.response.TripPlanInfoResponseDto;
import com.ssafy.enjoytrip.domain.plan.dto.request.CreatePlanRequestDto;
import com.ssafy.enjoytrip.domain.plan.service.PlanService;
import com.ssafy.enjoytrip.global.security.SecurityUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/trip/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    /**
     * 여행 계획 생성을 위한 도시 정보조회
     */
    @GetMapping
    public ResponseEntity<List<CityInfoResponseDto>> getCitiesForPlanning() {
        try {
            List<CityInfoResponseDto> cities =  planService.getCitiesForPlanning();
            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    /**
     * 특정 도시의 관광지 정보 조회
     */
    @GetMapping("/{cityCode}")
    public ResponseEntity<List<TourInfoResponseDto>> getToursByCity(@PathVariable Integer cityCode) {
        try {
            List<TourInfoResponseDto> tours = planService.getToursByCity(cityCode);
            return ResponseEntity.ok(tours);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 여행 계획 생성
     *
     * @param request 여행 계획 생성 요청 정보
     * @return 생성된 여행 계획 ID
     */

    @PostMapping
    public ResponseEntity<Long> createPlan (@RequestBody CreatePlanRequestDto request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Member member = securityUser.getMember();

        try {
            Long planId = planService.createPlan(member, request);
            return ResponseEntity.created(
                    URI.create("/api/v1/plans/detail/" + planId)
            ).body(planId);

        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);

        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    /**
     * 여행 계획 상세 조회
     */
    @GetMapping("/detail/{planId}")
    public ResponseEntity<TripPlanInfoResponseDto> getPlanDetail(@PathVariable Long planId) {
        try {
            TripPlanInfoResponseDto planDetail = planService.getPlanDetail(planId);
            return ResponseEntity.ok(planDetail);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 여행 계획 삭제
     */
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planId, @AuthenticationPrincipal SecurityUser securityUser) {
        try {
            planService.deletePlan(planId, securityUser.getMember());
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
