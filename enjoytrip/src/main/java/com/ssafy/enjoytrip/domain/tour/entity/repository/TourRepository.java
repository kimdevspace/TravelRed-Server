package com.ssafy.enjoytrip.domain.tour.entity.repository;

import com.ssafy.enjoytrip.domain.city.entity.City;
import com.ssafy.enjoytrip.domain.plan.dto.reponse.TourInfoResponseDto;
import com.ssafy.enjoytrip.domain.tour.dto.response.HomeTourResponseDto;
import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository  extends JpaRepository<Tour, Long> {
    @Query("SELECT new com.ssafy.enjoytrip.domain.tour.dto.response.HomeTourResponseDto(" +
            "t.id, t.tourName, t.address, t.backgroundImage, t.hit) " +
            "FROM Tour t ORDER BY t.hit DESC")
    List<HomeTourResponseDto> findTop20ByOrderByHitDesc(Pageable pageable);

    Tour findTourById(Long tourId);

    @Query("SELECT new com.ssafy.enjoytrip.domain.plan.dto.reponse.TourInfoResponseDto(" +
            "t.id, t.tourName, t.address, t.backgroundImage, " +
            "COALESCE(AVG(r.rating), 0), COUNT(DISTINCT r)) " +  // reviewRating → rating
            "FROM Tour t " +
            "LEFT JOIN t.reviews r " +
            "WHERE t.city = :city " +
            "GROUP BY t.id, t.tourName, t.address, t.backgroundImage")
    List<TourInfoResponseDto> findTourInfoByCity(@Param("city") City city);
}
