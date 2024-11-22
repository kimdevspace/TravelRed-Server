package com.ssafy.enjoytrip.domain.tour.entity;

import lombok.Getter;

@Getter
public enum ContentType {
    TOURIST_SPOT("관광지"),
    STAY("숙박"),
    RESTAURANT("음식점"),
    CULTURE("문화시설"),
    SHOW("공연"),
    TRAVEL("여행"),
    SHOPPING("쇼핑"),
    LEISURE("레저");

    private final String description;

    ContentType(String description) {
        this.description = description;
    }
}

