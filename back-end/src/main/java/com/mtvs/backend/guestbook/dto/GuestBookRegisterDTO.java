package com.mtvs.backend.guestbook.dto;

import lombok.Getter;

@Getter
public class GuestBookRegisterDTO {
    String content;
    String toUserId;
    String fromUserId;
    String registDate;

    public GuestBookRegisterDTO(String content, String toUserId, String fromUserId, String registDate) {
        this.content = content;
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.registDate = registDate;
    }
}
