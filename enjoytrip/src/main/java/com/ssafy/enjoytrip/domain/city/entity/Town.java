package com.ssafy.enjoytrip.domain.city.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "town")
public class Town {
    @Id
    @Column(name = "town_code")
    private Long id;

    @NotNull
    private String townName;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "city_code")
    private City city;

}

