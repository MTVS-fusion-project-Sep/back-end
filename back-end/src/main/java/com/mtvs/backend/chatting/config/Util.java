package com.mtvs.backend.chatting.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtvs.backend.chatting.domain.ChatMessage;
import org.springframework.web.socket.TextMessage;

import java.time.LocalDateTime;

public class Util {
    public static class Chat {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        public static TextMessage resolveTextMessage(ChatMessage message) {
            try {
                return new TextMessage(objectMapper.writeValueAsString(message));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        public static ChatMessage resolvePayload(String payload) {
            try {
                ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
                // 서버에 요청이 들어온 시점에 sentTime을 설정 (prePersist로 처리됨)
                chatMessage.setSentTime(LocalDateTime.now());
                return chatMessage;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}