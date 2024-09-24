package com.mtvs.backend.chatting.service;

import com.mtvs.backend.chatting.domain.ChatRoom;
import com.mtvs.backend.chatting.dto.ChatRoomRegistRequestDTO;
import com.mtvs.backend.chatting.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, ChatService chatService) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatService = chatService;
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom getChatRoomById(String roomId) {
        return chatRoomRepository.findById(roomId).orElse(null);
    }

    public void registChatRoom(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }
}
