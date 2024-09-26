package com.mtvs.backend.chatroominfo.dto;

import lombok.Getter;

@Getter
public class ChatRoomInfoRegisterDTO {

    private String userId;
    private String chatRoomId;

    public ChatRoomInfoRegisterDTO(String userId, String chatRoomId) {
        this.userId = userId;
        this.chatRoomId = chatRoomId;
    }

    public ChatRoomInfoRegisterDTO() {
    }
}
