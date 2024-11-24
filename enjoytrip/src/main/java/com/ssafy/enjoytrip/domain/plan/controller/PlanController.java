package com.ssafy.enjoytrip.domain.plan.controller;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.plan.dto.request.CreatePlanRequest;
import com.ssafy.enjoytrip.domain.plan.service.PlanService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api/v1/trip/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    /**
     * 여행 계획 생성
     *
     * @param member 현재 인증된 회원 정보
     * @param request 여행 계획 생성 요청 정보
     * @return 생성된 여행 계획 ID
     */

    @PostMapping
    public ResponseEntity<Long> createPlan (@AuthenticationPrincipal Member member, @RequestBody CreatePlanRequest request) {
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
