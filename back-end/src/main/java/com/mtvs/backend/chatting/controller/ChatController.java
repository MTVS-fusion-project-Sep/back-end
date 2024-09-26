package com.mtvs.backend.chatting.controller;

import com.mtvs.backend.chatting.domain.ChatMessage;
import com.mtvs.backend.chatting.domain.ChatRoom;
import com.mtvs.backend.chatting.dto.ChatRoomRegistRequestDTO;
import com.mtvs.backend.chatting.service.ChatMessageService;
import com.mtvs.backend.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final ChatMessageService chatMessageService;

    @GetMapping("/{roomId}")
    public ChatRoom getRoom(@PathVariable String roomId) {
        return chatService.findRoomById(roomId);
    }

    @GetMapping
    public List<ChatRoom> getAll() {
        return chatService.findAll();
    }

    @GetMapping("/category/{category}")
    public List<ChatRoom> getByCategory(@PathVariable String category) { return chatService.findRoomByCategory(category); }

    @GetMapping("/log")
    public List<ChatMessage> getChatMessages(@RequestParam String roomId, @RequestParam LocalDateTime entryTime) {
        return chatMessageService.getChatMessagesByRoomIdAfterEntryTime(roomId, entryTime);
    }

    @PostMapping
    public ChatRoom createRoom(@RequestBody ChatRoomRegistRequestDTO chatRoomRegistRequestDTO) {
        return chatService.createRoom(chatRoomRegistRequestDTO.getName(), chatRoomRegistRequestDTO.getCategory(), chatRoomRegistRequestDTO.getMaxCnt());
    }

    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable String roomId) {
        chatService.deleteRoomById(roomId);
    }
}
