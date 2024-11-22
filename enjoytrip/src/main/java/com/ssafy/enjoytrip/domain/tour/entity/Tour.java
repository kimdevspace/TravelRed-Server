package com.ssafy.enjoytrip.domain.tour.entity;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.city.entity.Town;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "tour")
public class Tour {

    @Id
    @Column(name = "tour_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private TourContent tourContent;

    @NotNull
    private String tourName;


    @NotNull
    private String address;

    private String zipCode;

    private String backgroundImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_code")
    private Town town;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer hit;

    @OneToOne(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TourDetail tourDetail;

    @Builder
    public Tour(TourContent tourContent, String tourName, String address, String zipCode,
                String backgroundImage, City city, Town town, Integer hit) {
        this.tourContent = tourContent;
        this.tourName = tourName;
        this.address = address;
        this.zipCode = zipCode;
        this.backgroundImage = backgroundImage;
        this.city = city;
        this.town = town;
        this.hit = hit;
    }

    // 연관관계 편의 메서드
    public void setTourDetail(TourDetail tourDetail) {
        this.tourDetail = tourDetail;
        tourDetail.setTour(this);  // 양방향 연관관계 설정
    }
}