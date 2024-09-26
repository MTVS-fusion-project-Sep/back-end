package com.mtvs.backend.chatroominfo.controller;

import com.mtvs.backend.chatroominfo.domain.ChatRoomInfo;
import com.mtvs.backend.chatroominfo.dto.ChatRoomInfoRegisterDTO;
import com.mtvs.backend.chatroominfo.service.ChatRoomInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-chatroom")
@RequiredArgsConstructor
public class ChatRoomInfoController {

    private final ChatRoomInfoServiceImpl userChatRoomInfoService;

    @PostMapping
    public ResponseEntity<ChatRoomInfo> enterRoom(@RequestBody ChatRoomInfoRegisterDTO chatRoomInfoRegisterDTO) {
        return ResponseEntity.ok(userChatRoomInfoService.enterChatRoom(chatRoomInfoRegisterDTO));
    }

}
