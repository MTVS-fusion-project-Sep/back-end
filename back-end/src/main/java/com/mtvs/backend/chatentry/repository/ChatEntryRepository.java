package com.mtvs.backend.chatentry.repository;

import com.mtvs.backend.chatentry.domain.ChatEntry;
import com.mtvs.backend.chatentry.domain.ChatEntryCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatEntryRepository extends JpaRepository<ChatEntry, ChatEntryCompositeKey> {
    ChatEntry getChatEntryByChatEntryCompositeKey(ChatEntryCompositeKey chatEntryCompositeKey);
    List<ChatEntry> getChatEntriesByChatEntryCompositeKey_UserId(String userId);
    boolean existsChatEntryByChatEntryCompositeKey(ChatEntryCompositeKey chatEntryCompositeKey);
}
