package com.ssafy.enjoytrip.domain.tour.service;

import com.ssafy.enjoytrip.domain.tour.dto.response.HomeTourResponseDto;
import com.ssafy.enjoytrip.domain.tour.entity.Tour;
import com.ssafy.enjoytrip.domain.tour.entity.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;

    public List<HomeTourResponseDto> getTopHitTours() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        return tourRepository.findTop20ByOrderByHitDesc(pageRequest);
    }

}
