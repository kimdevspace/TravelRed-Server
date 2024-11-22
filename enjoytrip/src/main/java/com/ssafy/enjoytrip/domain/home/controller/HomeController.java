package com.ssafy.enjoytrip.domain.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    // 이거 다시짜야됨! responseDto에 필요한 데이터 다 넣어서 전송해주기.
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("메인 페이지로 이동");
    }
}