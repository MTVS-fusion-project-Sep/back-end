package com.mtvs.backend.chatting.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Entity(name = "ChatMessage")
@Table(name = "chatmessage")
@NoArgsConstructor
@ToString
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK
    }

    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String roomId;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String userId;

    private String message;

    private LocalDateTime sentTime;

    // 엔티티가 저장되기 전에 sentTime을 자동으로 설정
    @PrePersist
    public void prePersist() {
        this.sentTime = LocalDateTime.now(); // 서버의 현재 시간으로 설정
    }
}