package com.mtvs.backend.chatting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtvs.backend.chatting.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;  // ObjectMapper 사용

    private static final String REDIS_KEY_PREFIX = "chat:room:";

    // Redis에서 페이징 처리된 메시지를 가져옴
    public List<ChatMessage> getMessagesFromRedis(String roomId, int page, int pageSize) {
        String redisKey = REDIS_KEY_PREFIX + roomId;
        long start = page * pageSize;
        long end = start + pageSize - 1;

        List<Object> redisMessages = redisTemplate.opsForList().range(redisKey, start, end); // Redis에서 Object로 데이터 조회
        return redisMessages.stream()
                .map(message -> objectMapper.convertValue(message, ChatMessage.class)) // Object to ChatMessage 변환
                .collect(Collectors.toList());
    }

    // Redis에 ChatMessage 저장
    public void saveChatMessage(ChatMessage chatMessage) {
        String key = REDIS_KEY_PREFIX + chatMessage.getRoomId();  // 채팅방별로 저장
        redisTemplate.opsForList().rightPush(key, chatMessage);
    }

    // Redis에서 특정 채팅방의 모든 메시지 가져오기
    public List<ChatMessage> getChatMessagesByAllPath(String path) {
        // 넘어오는 path는 chat:room:{roomId} 형식 이어야 한다
        List<Object> redisMessages = redisTemplate.opsForList().range(path, 0, -1);
        return redisMessages.stream()
                .map(message -> objectMapper.convertValue(message, ChatMessage.class))
                .collect(Collectors.toList());
    }

    // Redis에서 특정 채팅방의 메시지 삭제
    public void clearChatMessagesByAllPath(String path) {
        redisTemplate.delete(path); // 넘어오는 path는 chat:room:{roomId} 형식 이어야 한다
    }

    // Redis에 저장된 모든 채팅방 ID를 가져오는 로직
    public List<String> getAllRoomIds() {
        return redisTemplate.keys("chat:room:*").stream().toList(); // 예시로 채팅방 ID 리스트 반환
    }
}
