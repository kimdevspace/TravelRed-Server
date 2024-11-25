package com.ssafy.enjoytrip.domain.admin.plans.serivce;

import com.ssafy.enjoytrip.domain.admin.plans.dto.response.PlanAdminResponseDto;
import com.ssafy.enjoytrip.domain.plan.entity.Plan;
import com.ssafy.enjoytrip.domain.plan.entity.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanAdminService {
    private final PlanRepository planRepository;

    public Page<PlanAdminResponseDto> getAllPlans(Pageable pageable) {
        return planRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Transactional
    public void deletePlans(@RequestBody List<Long> planIds) {
        planRepository.deleteByIdIn(planIds);
    }

    private PlanAdminResponseDto toDto(Plan plan) {
        return PlanAdminResponseDto.builder()
                .planId(plan.getId())
                .memberName(plan.getMember().getNickname())  // 작성자의 닉네임을 가져옴
                .title(plan.getTitle())
                .totalDays(plan.getDay())
                .build();
    }
}
