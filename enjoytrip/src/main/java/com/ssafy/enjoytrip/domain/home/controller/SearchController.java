package com.ssafy.enjoytrip.domain.home.controller;

import com.ssafy.enjoytrip.domain.home.dto.response.HomeResponseDto;
import com.ssafy.enjoytrip.domain.home.dto.response.SearchResponseDto;
import com.ssafy.enjoytrip.domain.home.service.HomeService;
import com.ssafy.enjoytrip.domain.home.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trip/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<?> search() {
        SearchResponseDto searchResponseDto = searchService.makeSearchPage();
        return ResponseEntity.ok(searchResponseDto);
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<?> searchKeyWord(@PathVariable String keyword) {
        SearchResponseDto searchResponseDto = searchService.searchKeyWord(keyword);
        return ResponseEntity.ok(searchResponseDto);
    }

}
