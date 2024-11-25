package com.ssafy.enjoytrip.domain.plan.entity.repository;

import com.ssafy.enjoytrip.domain.plan.entity.Plan;
import com.ssafy.enjoytrip.domain.plan.entity.PlanTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanTripRepository extends JpaRepository<PlanTrip, Long> {
    @Query("SELECT pt FROM PlanTrip pt " +
            "JOIN FETCH pt.tour t " +
            "JOIN FETCH t.tourDetail td " +
            "WHERE pt.plan = :plan " +
            "ORDER BY pt.day ASC, pt.order ASC")
    List<PlanTrip> findByPlanOrderByDayAscOrderAsc(@Param("plan") Plan plan);
}
