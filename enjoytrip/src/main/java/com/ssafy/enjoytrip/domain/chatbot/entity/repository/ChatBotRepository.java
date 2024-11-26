package com.ssafy.enjoytrip.domain.chatbot.entity.repository;

import com.ssafy.enjoytrip.domain.chatbot.entity.ChatBot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatBotRepository extends JpaRepository<ChatBot, Long> {
}
