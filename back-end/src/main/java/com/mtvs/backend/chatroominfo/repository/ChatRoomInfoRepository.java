package com.mtvs.backend.chatroominfo.repository;

import com.mtvs.backend.chatroominfo.domain.ChatRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomInfoRepository extends JpaRepository<ChatRoomInfo, Long> {
}
