package com.mtvs.backend.chatroominfo.controller;

import com.mtvs.backend.chatroominfo.domain.ChatEntry;
import com.mtvs.backend.chatroominfo.service.ChatEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat/entry")
@RequiredArgsConstructor
public class ChatRoomInfoController {
    private final ChatEntryService chatEntryService;

    @GetMapping
    public ResponseEntity<ChatEntry> getChatEntryLogByRoomIdAndUserId
            (@RequestParam String roomId, @RequestParam String userId) {
        return ResponseEntity.ok(chatEntryService.getChatEntryLogsByRoomIdAndUserId(roomId, userId));
    }
}
