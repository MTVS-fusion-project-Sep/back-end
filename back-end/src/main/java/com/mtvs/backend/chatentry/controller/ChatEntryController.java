package com.mtvs.backend.chatentry.controller;

import com.mtvs.backend.chatentry.domain.ChatEntry;
import com.mtvs.backend.chatentry.service.ChatEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat/entry")
@RequiredArgsConstructor
public class ChatEntryController {
    private final ChatEntryService chatEntryService;

    @GetMapping
    public ResponseEntity<List<ChatEntry>> getChatEntryLogByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(chatEntryService.getChatEntryLogsByUserId(userId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ChatEntry> getChatEntryLogByRoomIdAndUserId
            (@PathVariable String roomId, @RequestParam String userId) {
        return ResponseEntity.ok(chatEntryService.getChatEntryLogsByRoomIdAndUserId(roomId, userId));
    }
}
