package com.mtvs.backend.chatting.controller;

import com.mtvs.backend.chatting.domain.ChatRoom;
import com.mtvs.backend.chatting.dto.ChatRoomRegistRequestDTO;
import com.mtvs.backend.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public List<ChatRoom> getAll() {
        return chatService.findAll();
    }

    @GetMapping("/category/{category}")
    public List<ChatRoom> getByCategory(@PathVariable String category) { return chatService.findRoomByCategory(category); }

    @PostMapping
    public ChatRoom createRoom(@RequestBody ChatRoomRegistRequestDTO chatRoomRegistRequestDTO) {
        return chatService.createRoom(chatRoomRegistRequestDTO.getName(), chatRoomRegistRequestDTO.getCategory(), chatRoomRegistRequestDTO.getMaxCnt());
    }

    @GetMapping("/{roomId}")
    public ChatRoom getRoom(@PathVariable String roomId) {
        return chatService.findRoomById(roomId);
    }
}
