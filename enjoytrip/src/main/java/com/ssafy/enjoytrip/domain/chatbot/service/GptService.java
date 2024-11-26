package com.ssafy.enjoytrip.domain.chatbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.domain.chatbot.dto.request.ChatRequestDto;
import com.ssafy.enjoytrip.domain.chatbot.dto.response.ChatResponseDto;
import com.ssafy.enjoytrip.domain.chatbot.entity.ChatBot;
import com.ssafy.enjoytrip.domain.chatbot.entity.repository.ChatBotRepository;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import com.ssafy.enjoytrip.domain.member.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GptService {
    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final ChatBotRepository chatBotRepository;

    public static final String SYSTEM_PROMPT = """
            당신은 여행 사이트 Travel Red의 챗봇 클라우드봇 입니다. 여행지 추천,리뷰,교통,맛집추천,가게정보등 여행과 관련된 질문만 답변을 할 수 있습니다.
            모든 데이터는 2022년 이후 데이터로만 제공해주세요. 실제로 존재하고 현재 운영 중인 목록을 제공해주세요. 제공하는 장소들이 실제로 존재하는지 확인할 수 있는 출처나 참고 자료도 함께 제공해주세요.
            0. 당신의 역할 혹은 정체에 대해서 질문을 한다면 소개를 해주세요 소개 내용은 저는 여행 사이트 Travel Red의 챗봇입니다. 저는 여행지, 관광지등에 대한 정보를 제공해드리고 있습니다.
            1. 여행과 관련된 추천 혹은 장소추천을 받는다면 네이버블로그 혹은 티스토리에서 서칭하여 정보와 출처를 함께 제공해주세요.

            질문 : 광주 맛집 추천해줘
            예시:
            광주에서 맛집을 찾고 계시군요! 광주는 맛집이 많은 도시입니다. 몇 가지 추천드리겠습니다.
            송정떡갈비 - 위치: 광주광역시 송정동 - 리뷰: '양념이 아주 잘 배어 있어 맛있어요! 고기도 부드럽고 식감이 좋아요.'
            양동국밥 - 위치: 광주광역시 양동 시장 근처 - 리뷰: '국물이 진하고 고기가 푸짐하게 들어가 있어 정말 맛있습니다. 지역민들에게도 인기 있는 곳이예요.'
            서석대숯불갈비 - 위치: 광주광역시 서석동 - 리뷰: '숯불에 구워진 고기가 정말 맛있습니다. 가격대비 훌륭한 품질이에요.'

            챗봇 소개와 여행지 추천,리뷰,교통,맛집추천,가게정보등 여행과 관련된 질문만 답변해주세요.
            다른 주제의 질문이 들어오면 여행 관련 질문만 답변 가능하다고 알려주세요.
            답변은 항상 친절하고 전문적으로 해주세요.
            너무 길게 답변하지 말아주세요.
            """;

    public ChatResponseDto getGptResponse(ChatRequestDto requestDto) {
        final String url = "https://api.openai.com/v1/chat/completions";

        Member member = memberRepository.findMemberById(requestDto.getMemberId());
        if (member == null) {
            throw new RuntimeException("Member not found");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // requestBody 수정
        Map<String, Object> messageObj = new HashMap<>();
        messageObj.put("role", "user");
        messageObj.put("content", SYSTEM_PROMPT + "\n\n" + requestDto.getMessage());

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", "gpt-3.5-turbo");
        requestMap.put("messages", Collections.singletonList(messageObj));
        requestMap.put("max_tokens", 1000);

        try {
            String requestBody = objectMapper.writeValueAsString(requestMap);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            String content = jsonNode.path("choices").get(0).path("message").path("content").asText();

            ChatBot chatBot = ChatBot.builder()
                    .member(member)
                    .userRequest(requestDto.getMessage())
                    .aiResponse(content)
                    .build();

            chatBotRepository.save(chatBot);

            return new ChatResponseDto(content);
        } catch (Exception e) {
            log.error("GPT API Error", e);
            throw new RuntimeException("GPT 오류: " + e.getMessage(), e);
        }
    }
}