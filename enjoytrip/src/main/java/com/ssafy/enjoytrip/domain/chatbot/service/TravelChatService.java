//package com.ssafy.enjoytrip.domain.chatbot.service;
//
//import com.ssafy.enjoytrip.domain.chatbot.dto.request.ChatBotRequestDto;
//import com.ssafy.enjoytrip.domain.chatbot.entity.ChatBot;
//import com.ssafy.enjoytrip.domain.chatbot.entity.ChatBotRepository;
//import com.ssafy.enjoytrip.domain.member.entity.Member;
//import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
//import org.springframework.ai.chat.ChatResponse;
//import org.springframework.ai.chat.messages.Message;
//import org.springframework.ai.chat.messages.UserMessage;
//import org.springframework.ai.chat.messages.SystemMessage;
//import java.util.Arrays;
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class TravelChatService {
//    private final AiService aiService;
//    private final ChatBotRepository chatBotRepository;
//    private final MemberRepository memberRepository;
//
//    private static final String SYSTEM_PROMPT = """
//        You are a helpful travel advisor. Provide personalized travel recommendations based on user preferences.
//        Keep responses under 200 words. Include:
//        1. 2-3 destination recommendations
//        2. Best time to visit
//        3. Main attractions
//        Format recommendations in clear, concise Korean.
//        """;
//
//    public String chat(ChatBotRequestDto request) {
//        Member member = memberRepository.findById(request.memberId())
//                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
//
//        SystemMessage systemMessage = new SystemMessage(SYSTEM_PROMPT);
//        UserMessage userMessage = new UserMessage(request.userRequest());
//
//        String aiResponse = aiService.generateResponse(Arrays.asList(systemMessage, userMessage));
//
//        ChatBot chatBot = ChatBot.builder()
//                .member(member)
//                .userRequest(request.userRequest())
//                .botResponse(aiResponse)
//                .build();
//
//        chatBotRepository.save(chatBot);
//        return aiResponse;
//    }
//}