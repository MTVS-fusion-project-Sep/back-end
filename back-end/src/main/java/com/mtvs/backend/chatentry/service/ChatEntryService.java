package com.mtvs.backend.chatentry.service;

import com.mtvs.backend.chatentry.domain.ChatEntry;
import com.mtvs.backend.chatentry.domain.ChatEntryCompositeKey;
import com.mtvs.backend.chatentry.repository.ChatEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatEntryService {
    private final ChatEntryRepository chatEntryRepository;

    public ChatEntry getChatEntryLogsByRoomIdAndUserId(String roomId, String userId) {
        return chatEntryRepository.getChatEntryByChatEntryCompositeKey(new ChatEntryCompositeKey(roomId, userId));
    };

    public List<ChatEntry> getChatEntryLogsByUserId(String userId) {
        return chatEntryRepository.getChatEntriesByChatEntryCompositeKey_UserId(userId);
    }
}
