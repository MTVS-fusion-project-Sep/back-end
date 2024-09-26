package com.mtvs.backend.chatroominfo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter @Setter
public class ChatRoomInfo {

    private String chatRoomId;
    private LocalDateTime enteredAt;

    public ChatRoomInfo(String chatRoomId, LocalDateTime enteredAt) {
        this.chatRoomId = chatRoomId;
        this.enteredAt = enteredAt;
    }

    public ChatRoomInfo() {
    }
}
