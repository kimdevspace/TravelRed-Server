package com.ssafy.enjoytrip.domain.chatbot.entity;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "chat_bot")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatBot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "user_request", length = 500)
    private String userRequest;

    @Column(name = "ai_response", columnDefinition = "TEXT")
    private String aiResponse;

    @Builder
    public ChatBot(Member member, String userRequest, String aiResponse) {
        this.member = member;
        this.userRequest = userRequest;
        this.aiResponse = aiResponse;
    }
}