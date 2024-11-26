package com.ssafy.enjoytrip.domain.chatbot.controller;

import com.ssafy.enjoytrip.domain.chatbot.dto.request.ChatRequestDto;
import com.ssafy.enjoytrip.domain.chatbot.dto.response.ChatResponseDto;
import com.ssafy.enjoytrip.domain.chatbot.service.GptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/chat")
public class ChatGPTController {
    private final GptService gptService;

    @PostMapping("/ask")
    public ResponseEntity<?> askGpt(@RequestBody ChatRequestDto chatRequestDto) {
        ChatResponseDto result = gptService.getGptResponse(chatRequestDto);
        return ResponseEntity.ok(result);
    }
}