package com.ssafy.enjoytrip.domain.tour.entity;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.city.entity.Town;
import com.ssafy.enjoytrip.domain.review.entity.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "content_id")
    private TourContent tourContent;

    @NotNull
    private String tourName;

    @NotNull
    private String address;

    private String zipCode;

    private String backgroundImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_code", insertable = false, updatable = false)
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "town_code", referencedColumnName = "town_code"),
            @JoinColumn(name = "city_code", referencedColumnName = "city_code")
    })
    private Town town;

    private Integer hit;

    @OneToOne(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TourDetail tourDetail;

    @OneToMany(mappedBy = "tour")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Tour(TourContent tourContent, String tourName, String address, String zipCode,
                String backgroundImage, Town town) {
        this.tourContent = tourContent;
        this.tourName = tourName;
        this.address = address;
        this.zipCode = zipCode;
        this.backgroundImage = backgroundImage;
        this.city = town.getCity();
        this.town = town;
        this.hit = 0;
    }

    // 연관관계 편의 메서드
    public void setTourDetail(TourDetail tourDetail) {
        this.tourDetail = tourDetail;
        tourDetail.setTour(this);  // 양방향 연관관계 설정
    }
}