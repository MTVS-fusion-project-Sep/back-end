package com.mtvs.backend.chatting.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomRegistRequestDTO {
    String name;
    String category;
    int maxCnt;
}
