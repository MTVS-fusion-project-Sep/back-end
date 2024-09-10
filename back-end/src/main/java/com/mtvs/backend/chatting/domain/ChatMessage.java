package com.mtvs.backend.chatting.domain;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, QUIT
    }

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;
}