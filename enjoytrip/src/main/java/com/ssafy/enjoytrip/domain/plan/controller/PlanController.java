package com.ssafy.enjoytrip.domain.plan.controller;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.plan.dto.reponse.CityInfoResponse;
import com.ssafy.enjoytrip.domain.plan.dto.request.CreatePlanRequest;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/trip/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    /**
     * 여행 계획 생성을 위한 도시 정보 조회
     */
    @GetMapping
    public ResponseEntity<List<CityInfoResponse>> getCitiesForPlanning() {
        try {
            List<CityInfoResponse> cities =  planService.getCitiesForPlanning();
            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    /**
     * 여행 계획 생성
     *
     * @param request 여행 계획 생성 요청 정보
     * @return 생성된 여행 계획 ID
     */

    @PostMapping
    public ResponseEntity<Long> createPlan (@RequestBody CreatePlanRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Member member = securityUser.getMember();

        try {
            Long planId = planService.createPlan(member, request);
            return ResponseEntity.created(
                    URI.create("/api/plans/" + planId)
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

}
