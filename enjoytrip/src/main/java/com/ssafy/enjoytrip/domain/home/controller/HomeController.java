package com.ssafy.enjoytrip.domain.home.controller;

import com.ssafy.enjoytrip.domain.home.dto.response.HomeResponseDto;
import com.ssafy.enjoytrip.domain.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HomeController {

    private final HomeService homeService;

    // 이거 다시짜야됨! responseDto에 필요한 데이터 다 넣어서 전송해주기.

    // 여행지를 조회수 순서대로 정렬하여 대충 20개 뿌려주자.
    // 리뷰를 좋아요 개수가 많은 순서대로 정렬하여 대충 20개 뿌려주자.
    // 공지사항을 최근 순서대로 10개 뿌려주자.

    @GetMapping("/home")
    public ResponseEntity<HomeResponseDto> home() {
        HomeResponseDto homeResponseDto = homeService.makeHomePage();
        return ResponseEntity.ok(homeResponseDto);
    }


}