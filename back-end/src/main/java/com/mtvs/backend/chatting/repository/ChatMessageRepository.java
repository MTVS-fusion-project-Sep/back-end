package com.mtvs.backend.chatting.repository;

import com.mtvs.backend.chatting.domain.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findChatMessagesByRoomId(String roomId);
    List<ChatMessage> findChatMessagesByRoomIdAndSentTimeAfter(String roomId, LocalDateTime sentTime);
    Page<ChatMessage> findChatMessagesByRoomIdAndSentTimeAfter(String roomId, LocalDateTime sentTime, Pageable pageable);
}
