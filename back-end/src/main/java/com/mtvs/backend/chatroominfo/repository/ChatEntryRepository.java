package com.mtvs.backend.chatroominfo.repository;

import com.mtvs.backend.chatroominfo.domain.ChatEntry;
import com.mtvs.backend.chatroominfo.domain.ChatEntryCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEntryRepository extends JpaRepository<ChatEntry, ChatEntryCompositeKey> {
    ChatEntry getChatEntryByChatEntryCompositeKey(ChatEntryCompositeKey chatEntryCompositeKey);
    boolean existsChatEntryByChatEntryCompositeKey(ChatEntryCompositeKey chatEntryCompositeKey);
}
