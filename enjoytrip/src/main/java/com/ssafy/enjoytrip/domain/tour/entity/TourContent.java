package com.ssafy.enjoytrip.domain.tour.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "tour_content")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_name", nullable = false)
    private ContentType contentName;

    @Column(name = "kor_name", nullable = false)
    private String korName;

    // ContentType Enum 정의
    public enum ContentType {
        TOURIST_SPOT,
        STAY,
        RESTAURANT,
        CULTURE,
        SHOW,
        TRAVEL,
        SHOPPING,
        LEISURE
    }
}