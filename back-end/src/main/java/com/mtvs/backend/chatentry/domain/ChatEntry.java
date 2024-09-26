package com.mtvs.backend.chatentry.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter @Setter
public class ChatEntry {
    @EmbeddedId
    private ChatEntryCompositeKey chatEntryCompositeKey;
    private LocalDateTime entryTime;

    public ChatEntry() {}

    public ChatEntry(ChatEntryCompositeKey chatEntryCompositeKey) {
        this.chatEntryCompositeKey = chatEntryCompositeKey;
    }

    // 엔티티가 저장되기 전에 자동으로 현재 시간을 설정
    @PrePersist
    protected void onCreate() {
        this.entryTime = LocalDateTime.now();
    }
}
