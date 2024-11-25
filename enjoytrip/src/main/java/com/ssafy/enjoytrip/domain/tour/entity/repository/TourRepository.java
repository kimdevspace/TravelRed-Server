package com.ssafy.enjoytrip.domain.tour.entity.repository;

import com.ssafy.enjoytrip.domain.tour.dto.response.HomeTourResponseDto;
import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.jpa.HibernateHints.HINT_FETCH_SIZE;

@Repository
public interface TourRepository  extends JpaRepository<Tour, Long> {
    @Query("SELECT new com.ssafy.enjoytrip.domain.tour.dto.response.HomeTourResponseDto(" +
            "t.id, t.tourName, t.address, t.backgroundImage, t.hit) " +
            "FROM Tour t ORDER BY t.hit DESC")
    List<HomeTourResponseDto> findTop20ByOrderByHitDesc(Pageable pageable);

    Tour findTourById(Long tourId);
}
