package com.mtvs.backend.chatroominfo.service;

import com.mtvs.backend.chatroominfo.domain.ChatEntry;
import com.mtvs.backend.chatroominfo.domain.ChatEntryCompositeKey;
import com.mtvs.backend.chatroominfo.repository.ChatEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatEntryService {
    private final ChatEntryRepository chatEntryRepository;

    public ChatEntry getChatEntryLogsByRoomIdAndUserId(String roomId, String userId) {
        return chatEntryRepository.getChatEntriesByChatEntryCompositeKey(new ChatEntryCompositeKey(roomId, userId));
    };
}
