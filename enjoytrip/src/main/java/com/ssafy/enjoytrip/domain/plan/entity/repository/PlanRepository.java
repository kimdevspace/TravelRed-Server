package com.ssafy.enjoytrip.domain.plan.entity.repository;

import com.ssafy.enjoytrip.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    void deleteByIdIn(List<Long> planIds);
    Long countByMemberId(Long memberId);  // 추가
}
