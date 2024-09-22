package com.mtvs.backend.guestbook.dto;

import lombok.Getter;

@Getter
public class GuestBookUpdateDTO {
    private Long id;
    private String content;
    private String toUserId;
    private String fromUserId;
    private String registDate;

    public GuestBookUpdateDTO() {
    }

    public GuestBookUpdateDTO(Long id, String content, String toUserId, String fromUserId, String registDate) {
        this.id = id;
        this.content = content;
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.registDate = registDate;
    }
}
