package com.mtvs.backend.chatting.service;

import com.mtvs.backend.chatting.domain.ChatMessage;
import com.mtvs.backend.chatting.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatSchedulerService {
    @Autowired
    private ChatRedisService chatRedisService;

    @Autowired
    private ChatMessageRepository chatMessageRepository;  // DB에 저장하는 JPA 리포지토리

    // 1분마다 실행되도록 설정 (cron 표현식을 수정하여 특정 시간대에 실행 가능)
    // 1분으로 설정한 이유는 채팅은 실시간 데이터 처리가 중요하기도 하고, 채팅메세지를 저장하는 것이므로
    // 트래픽이 많을 예정이기에 적당히 짧은 간격인 1분으로 설정
    @Scheduled(cron = "0 * * * * *") // 초, 분, 시, 일, 월, 요일 순으로 스케쥴링이 가능
    public void storeMessagesFromRedisToDb() {
        // Redis에 저장된 모든 채팅방의 메시지를 가져와 DB에 저장
        List<String> roomIdPaths = chatRedisService.getAllRoomIds(); // Redis에서 채팅방 ID 리스트 가져오는 로직 추가 필요
        for (String roomIdPath : roomIdPaths) {
            List<ChatMessage> messages = chatRedisService.getChatMessagesByAllPath(roomIdPath);
            chatMessageRepository.saveAll(messages);  // 일괄 저장
            chatRedisService.clearChatMessagesByAllPath(roomIdPath);  // 저장 후 Redis 데이터 삭제
            if(!messages.isEmpty()) System.out.println("Redis에 있는 ChatMessage 일괄 저장 완료");
            else System.out.println("Redis에 저장된 ChatMessage가 없습니다");
        }
    }
}
