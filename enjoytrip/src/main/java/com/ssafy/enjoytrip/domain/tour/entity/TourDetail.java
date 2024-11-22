package com.ssafy.enjoytrip.domain.tour.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "tour_detail")
public class TourDetail {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId  // 부모 테이블의 기본키를 매핑
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @NotNull
    private String cityCode;

    @NotNull
    private String description;

    private String telephone;

    @Column(precision = 11, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;

    @Builder
    public TourDetail(Tour tour, String cityCode, String description, String telephone,
                      BigDecimal latitude, BigDecimal longitude) {
        this.tour = tour;
        this.cityCode = cityCode;
        this.description = description;
        this.telephone = telephone;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
