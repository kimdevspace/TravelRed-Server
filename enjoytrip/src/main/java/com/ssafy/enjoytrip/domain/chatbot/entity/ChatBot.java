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
    @Column(name = "bot_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "user_request", length = 500)
    private String userRequest;

    @Column(name = "bot_response", columnDefinition = "TEXT")
    private String botResponse;

    @Builder
    public ChatBot(Member member, String userRequest, String botResponse) {
        this.member = member;
        this.userRequest = userRequest;
        this.botResponse = botResponse;
    }
}