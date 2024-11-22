package com.ssafy.enjoytrip.domain.plan.entity;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.tour.entity.TourContent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "plan")
public class Plan {
    @Id
    @Column(name = "plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code", nullable = false)
    private City city;

    @NotNull
    private String planTitle;

    private String thumbnailImage;

    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public Plan(Member member, City city, String planTitle,
                String thumbnailImage, LocalDate startDate, LocalDate endDate) {
        this.member = member;
        this.city = city;
        this.planTitle = planTitle;
        this.thumbnailImage = thumbnailImage;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
