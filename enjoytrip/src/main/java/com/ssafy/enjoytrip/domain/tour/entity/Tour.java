package com.ssafy.enjoytrip.domain.tour.entity;

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

    @NotNull
    private String tourName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ContentType contentType;  // 이 필드의 getDescription()으로 한글 이름 접근 가능

    @NotNull
    private String address;

    private String zipCode;

    private String backgroundImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_code")
    private Town town;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer hit;

    @OneToOne(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TourDetail tourDetail;

    @Builder
    public Tour(String tourName, ContentType contentType, String address, String zipCode,
                String backgroundImage, Town town, Integer hit) {
        this.tourName = tourName;
        this.contentType = contentType;
        this.address = address;
        this.zipCode = zipCode;
        this.backgroundImage = backgroundImage;
        this.town = town;
        this.hit = hit;
    }

    // 연관관계 편의 메서드
    public void setTourDetail(TourDetail tourDetail) {
        this.tourDetail = tourDetail;
        tourDetail.setTour(this);  // 양방향 연관관계 설정
    }
}