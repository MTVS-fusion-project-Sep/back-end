package com.mtvs.backend.chatting.repository;

import com.mtvs.backend.chatting.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findChatMessagesByRoomId(String roomId);
}
