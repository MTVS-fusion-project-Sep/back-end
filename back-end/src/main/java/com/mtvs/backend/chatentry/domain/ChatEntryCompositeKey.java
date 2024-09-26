package com.mtvs.backend.chatentry.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntryCompositeKey {
    private String roomId;
    private String userId;
}
