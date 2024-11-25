package com.ssafy.enjoytrip.domain.admin.plans.controller;

import com.ssafy.enjoytrip.domain.admin.plans.dto.response.PlanAdminResponseDto;
import com.ssafy.enjoytrip.domain.admin.plans.serivce.PlanAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/plans")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class PlanAdminController {
    private final PlanAdminService planAdminService;

    //모든 계획 조회
    @GetMapping
    public ResponseEntity<Page<PlanAdminResponseDto>> getAllPlans(Pageable pageable) {
        return ResponseEntity.ok(planAdminService.getAllPlans(pageable));
    }

    // 선택된 계획들 일괄 삭제하기
    @DeleteMapping
    public ResponseEntity<Void> deletePlans(@RequestBody List<Long> planIds) {
        planAdminService.deletePlans(planIds);
        return ResponseEntity.ok().build();
    }
}
