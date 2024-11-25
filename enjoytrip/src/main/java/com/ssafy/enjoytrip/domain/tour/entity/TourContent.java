package com.ssafy.enjoytrip.domain.tour.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tour_content")
public class TourContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ContentType contentName;

    @NotNull
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