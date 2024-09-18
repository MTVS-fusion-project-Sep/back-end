package com.mtvs.backend.chatting.controller;

import com.mtvs.backend.chatting.domain.ChatRoom;
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

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping("/{roomId}")
    public ChatRoom getRoom(@PathVariable String roomId) {
        return chatService.findRoomById(roomId);
    }
}
