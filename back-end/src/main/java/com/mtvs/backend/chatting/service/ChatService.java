package com.mtvs.backend.chatting.service;

import com.mtvs.backend.chatting.config.Util;
import com.mtvs.backend.chatting.domain.ChatMessage;
import com.mtvs.backend.chatting.domain.ChatRoom;
import com.mtvs.backend.chatting.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public List<ChatRoom> findAll() {
        return chatRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRepository.findById(roomId);
    }

    public ChatRoom createRoom(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.of(roomId, name);
        chatRepository.save(roomId, chatRoom);
        return chatRoom;
    }

    public void handleAction(
            String roomId,
            WebSocketSession session,
            ChatMessage chatMessage
    ) {
        ChatRoom room = findRoomById(roomId);

        if (isEnterRoom(chatMessage)) {
            room.join(session);
            chatMessage.setMessage(chatMessage.getSender() + "님 환영합니다.");
        }

        TextMessage textMessage = Util.Chat.resolveTextMessage(chatMessage);
        room.sendMessage(textMessage);
    }

    private boolean isEnterRoom(ChatMessage chatMessage) {
        return chatMessage.getMessageType().equals(ChatMessage.MessageType.ENTER);
    }
}