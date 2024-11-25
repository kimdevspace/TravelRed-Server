package com.ssafy.enjoytrip.domain.plan.entity;

import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plan_trip")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_trip_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(name = "day", nullable = false)
    private Integer day;

    @Column(name = "`order`", nullable = false)
    private Integer order;

    @Builder
    private PlanTrip(Plan plan, Tour tour, Integer day, Integer order) {
        // 필수값 검증
        if (plan == null || tour == null || day == null || order == null) {
            throw new IllegalArgumentException("필수 값이 누락되었습니다.");
        }

        this.plan = plan;
        this.tour = tour;
        this.day = day;
        this.order = order;
    }
}