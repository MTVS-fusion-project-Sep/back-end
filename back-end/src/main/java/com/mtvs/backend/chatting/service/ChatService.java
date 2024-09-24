package com.mtvs.backend.chatting.service;

import com.mtvs.backend.chatting.config.Util;
import com.mtvs.backend.chatting.domain.ChatMessage;
import com.mtvs.backend.chatting.domain.ChatRoom;
import com.mtvs.backend.chatting.repository.ChatMessageRepository;
import com.mtvs.backend.chatting.repository.ChatRepository;
import com.mtvs.backend.chatting.repository.ChatRoomRepository;
import com.mtvs.backend.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public List<ChatRoom> findAll() {
        return chatRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRepository.findById(roomId);
    }

    public List<ChatRoom> findRoomByCategory(String category) { return chatRepository.findByCategory(category); }

    public ChatRoom createRoom(String name, String category, int maxCnt) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.of(roomId, name, category, maxCnt);
        chatRepository.save(roomId, chatRoom);
        chatRoomRepository.save(chatRoom);
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
            room.setHeadCnt(room.getHeadCnt() + 1);
            chatMessage.setMessage(userRepository.findByUserId(chatMessage.getUserId()).getUserNickname() + "님 환영합니다.");
        }

        TextMessage textMessage = Util.Chat.resolveTextMessage(chatMessage);
        chatMessageRepository.save(chatMessage);
        System.out.println("textMessage = " + textMessage);
        room.sendMessage(textMessage);
    }

    private boolean isEnterRoom(ChatMessage chatMessage) {
        return chatMessage.getMessageType().equals(ChatMessage.MessageType.ENTER);
    }

    public void removeSession(WebSocketSession session) {
        chatRepository.findAll().forEach(chatRoom -> {
            if(chatRoom.remove(session)) chatRoom.setHeadCnt(chatRoom.getHeadCnt() - 1);
        });
    }

    public void deleteRoomById(String roomId) {
        chatRepository.deleteById(roomId);
    }
}