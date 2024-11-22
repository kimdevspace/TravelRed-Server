package com.ssafy.enjoytrip.domain.city.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @NotNull
    @JoinColumn(name = "city_code", referencedColumnName = "city_code")
    private City city;

    @NotNull
    private String townName;
}
