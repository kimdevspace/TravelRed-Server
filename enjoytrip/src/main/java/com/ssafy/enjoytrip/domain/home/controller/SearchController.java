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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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
    public ResponseEntity<?> searchKeyWord(@PathVariable(value = "keyword") String keyword) {
        try {
            String decodedKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8.toString());
            SearchResponseDto searchResponseDto = searchService.searchKeyWord(decodedKeyword);
            return ResponseEntity.ok(searchResponseDto);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.badRequest().body("Invalid encoding");
        }
    }

}
