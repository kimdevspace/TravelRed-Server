package com.ssafy.enjoytrip.domain.plan.entity.repository;

import com.ssafy.enjoytrip.domain.plan.dto.response.SearchPlanResponseDto;
import com.ssafy.enjoytrip.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    void deleteByIdIn(List<Long> planIds);
    Long countByMemberId(Long memberId);  // 추가

    @Query("SELECT new com.ssafy.enjoytrip.domain.plan.dto.response.SearchPlanResponseDto(" +
            "p.id, " +           // planId
            "p.title, " +        // title
            "p.thumbnailImage, " + // thumbnailImage
            "p.startDate, " +    // startDate (LocalDate)
            "p.endDate, " +      // endDate (LocalDate)
            "m.id, " +           // memberId
            "m.profileImage, " + // profileImage
            "m.nickname) " +     // nickname
            "FROM Plan p " +
            "JOIN p.member m " +
            "ORDER BY p.id DESC")
    List<SearchPlanResponseDto> findAllPlansWithMemberInfo();

    List<Plan> findByMemberId(Long memberId);
}
