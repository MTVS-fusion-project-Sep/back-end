package com.mtvs.backend.chatting.service;

import com.mtvs.backend.chatroominfo.domain.ChatEntry;
import com.mtvs.backend.chatroominfo.domain.ChatEntryCompositeKey;
import com.mtvs.backend.chatroominfo.repository.ChatEntryRepository;
import com.mtvs.backend.chatting.domain.ChatMessage;
import com.mtvs.backend.chatting.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatEntryRepository chatEntryRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, ChatEntryRepository chatEntryRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatEntryRepository = chatEntryRepository;
    }

    public List<ChatMessage> getAllChatMessages(String roomId) {
        return chatMessageRepository.findChatMessagesByRoomId(roomId);
    }

    public List<ChatMessage> getChatMessagesByRoomIdAfterEntryTime(String roomId, String userId) {
        ChatEntry chatEntry = chatEntryRepository.getChatEntryByChatEntryCompositeKey(new ChatEntryCompositeKey(roomId, userId));
        if (chatEntry == null) throw new IllegalArgumentException("잘못된 userId로 호출");
        return chatMessageRepository.findChatMessagesByRoomIdAndSentTimeAfter(roomId, chatEntry.getEntryTime());
    }
}
