package com.ssafy.enjoytrip.domain.city.entity.repository;

import com.ssafy.enjoytrip.domain.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
