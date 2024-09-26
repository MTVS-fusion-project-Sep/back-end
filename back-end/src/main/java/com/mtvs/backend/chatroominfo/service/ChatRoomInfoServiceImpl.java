package com.mtvs.backend.chatroominfo.service;

import com.mtvs.backend.chatroominfo.dto.ChatRoomInfoRegisterDTO;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.service.UserServiceImpl;
import com.mtvs.backend.chatroominfo.domain.ChatRoomInfo;
import com.mtvs.backend.chatroominfo.repository.ChatRoomInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatRoomInfoServiceImpl implements ChatRoomInfoService{
    private final UserServiceImpl userServiceImpl;
    private final ChatRoomInfoRepository userChatRoomInfoRepository;

    public ChatRoomInfo enterChatRoom(ChatRoomInfoRegisterDTO crirDTO) {
        User user = userServiceImpl.getUserByUserId(crirDTO.getUserId());

        ChatRoomInfo userChatRoomInfo = new ChatRoomInfo();

        userChatRoomInfo.setChatRoomId(crirDTO.getChatRoomId());
        userChatRoomInfo.setEnteredAt(LocalDateTime.now());

        userChatRoomInfoRepository.save(userChatRoomInfo);

        return userChatRoomInfo;
    }

}
