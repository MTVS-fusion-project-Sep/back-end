package com.mtvs.backend.chatting.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Entity(name = "ChatMessage")
@Table(name = "chatmessage")
@NoArgsConstructor
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, QUIT
    }

    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private String roomId;
    private MessageType messageType;
    private String userId;
    private String message;
    private LocalDateTime sentTime;
}