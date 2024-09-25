package com.mtvs.backend.chatting.service;

import com.mtvs.backend.chatting.domain.ChatMessage;
import com.mtvs.backend.chatting.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public List<ChatMessage> getAllChatMessages(String roomId) {
        return chatMessageRepository.findChatMessagesByRoomId(roomId);
    }

    public List<ChatMessage> getChatMessagesByUserIdAndRoomId(String userId, String roomId) {
        return chatMessageRepository.findChatMessagesByRoomIdAndSentTimeAfter(roomId, LocalDateTime.now());
        // user에 저장된 localDateTime을 2번째파라미터로 넣어줘야 함
    }
}
