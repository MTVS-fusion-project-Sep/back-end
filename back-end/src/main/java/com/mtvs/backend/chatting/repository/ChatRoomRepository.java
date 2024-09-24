package com.mtvs.backend.chatting.repository;

import com.mtvs.backend.chatting.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
}
