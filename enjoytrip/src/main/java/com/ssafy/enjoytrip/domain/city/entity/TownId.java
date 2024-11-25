package com.ssafy.enjoytrip.domain.city.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Town 복합키 사용을 위한 클래스
// 아래와 같이 사용하면 됨

/*
TownId townId = new TownId(1, 1);  // townCode, cityCode
Town town = Town.builder()
    .id(townId)
    .townName("강남동")
    .city(city)
    .build();
 */
@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TownId implements Serializable {

    @Column(name = "town_code")
    private Integer townCode;

    @Column(name = "city_code")
    private Integer cityCode;
}

