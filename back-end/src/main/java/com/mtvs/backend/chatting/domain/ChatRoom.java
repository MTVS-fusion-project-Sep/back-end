package com.mtvs.backend.chatting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "ChatRoom")
@Table(name = "chatroom")
@NoArgsConstructor(force = true)
public class ChatRoom {
    @Id
    @Column(name = "ROOM_ID")
    private final String roomId;
    private final String name;
    private final String category;
    private int headCnt;
    private final int maxCnt;

    @Transient
    @JsonIgnore
    private final Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name, String category, int maxCnt) {
        this.roomId = roomId;
        this.name = name;
        this.category = category;
        this.headCnt = 0; // 채팅방 생성시 기존 인원은 무조건 0명임
        this.maxCnt = maxCnt;
    }

    public void setHeadCnt(int headCnt) {
        this.headCnt = headCnt;
    }

    public void sendMessage(TextMessage message) {
        this.getSessions()
                .parallelStream()
                .forEach(session -> sendMessageToSession(session, message));
    }

    private void sendMessageToSession(WebSocketSession session, TextMessage message) {
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void join(WebSocketSession session) {
        sessions.add(session);
    }

    public boolean remove(WebSocketSession session) { return sessions.remove(session); }

    public static ChatRoom of(String roomId, String name, String category, int maxCnt) {
        return ChatRoom.builder()
                .roomId(roomId)
                .name(name)
                .category(category)
                .maxCnt(maxCnt)
                .build();
    }
}