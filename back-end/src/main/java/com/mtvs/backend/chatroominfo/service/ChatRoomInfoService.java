package com.mtvs.backend.chatroominfo.service;

import com.mtvs.backend.chatroominfo.domain.ChatRoomInfo;
import com.mtvs.backend.chatroominfo.dto.ChatRoomInfoRegisterDTO;

public interface ChatRoomInfoService {
    ChatRoomInfo enterChatRoom(ChatRoomInfoRegisterDTO crirDTO);
}
