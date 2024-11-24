package com.ssafy.enjoytrip.domain.plan.entity.repository;

import com.ssafy.enjoytrip.domain.plan.entity.PlanTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanTripRepository extends JpaRepository<PlanTrip, Long> {
}
