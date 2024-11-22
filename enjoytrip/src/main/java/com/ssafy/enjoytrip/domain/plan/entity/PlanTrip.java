package com.ssafy.enjoytrip.domain.plan.entity;

import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "plan_trip")
public class PlanTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_trip_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @Column(name = "day")
    private Integer day;

    @Column(name = "`order`")
    private Integer order;

    @Builder
    public PlanTrip(Plan plan, Tour tour, Integer day, Integer order) {
        this.plan = plan;
        this.tour = tour;
        this.day = day;
        this.order = order;
    }
}