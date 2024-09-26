package com.mtvs.backend.chatting.service;

import com.mtvs.backend.chatentry.domain.ChatEntry;
import com.mtvs.backend.chatentry.domain.ChatEntryCompositeKey;
import com.mtvs.backend.chatentry.repository.ChatEntryRepository;
import com.mtvs.backend.chatting.domain.ChatMessage;
import com.mtvs.backend.chatting.repository.ChatMessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatEntryRepository chatEntryRepository;

    private static final int PAGE_SIZE = 15;
    private final ChatRedisService chatRedisService;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, ChatEntryRepository chatEntryRepository, ChatRedisService chatRedisService) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatEntryRepository = chatEntryRepository;
        this.chatRedisService = chatRedisService;
    }

    public List<ChatMessage> getAllChatMessages(String roomId) {
        return chatMessageRepository.findChatMessagesByRoomId(roomId);
    }

    public List<ChatMessage> getChatMessagesByRoomIdAfterEntryTime(String roomId, String userId, int page) {
        // 1. 유저가 방에 들어간 시간을 가져옴
        ChatEntry chatEntry = chatEntryRepository
                .getChatEntryByChatEntryCompositeKey(new ChatEntryCompositeKey(roomId, userId));
        if (chatEntry == null) throw new IllegalArgumentException("잘못된 userId로 호출");

        // 2. Redis에서 최신 메시지를 우선 가져옴 (페이징 적용)
        List<ChatMessage> redisMessages = chatRedisService.getMessagesFromRedis(roomId, page, PAGE_SIZE);

        // 3. Redis에서 가져온 메시지가 충분하지 않다면 MySQL에서 추가로 가져옴
        if (redisMessages.size() < PAGE_SIZE) {
            // 남은 메시지 수만큼 DB에서 가져오기 (내림차순으로 최신 메시지부터)
            Pageable pageable = PageRequest.of(page, PAGE_SIZE - redisMessages.size(), Sort.by(Sort.Direction.DESC, "sentTime"));
            Page<ChatMessage> dbMessagesPage = chatMessageRepository
                    .findChatMessagesByRoomIdAndSentTimeAfter(roomId, chatEntry.getEntryTime(), pageable);

            List<ChatMessage> dbMessages = dbMessagesPage.getContent()
                    .stream()
                    .sorted(Comparator.comparing(ChatMessage::getSentTime)) // 오름차순 정렬
                    .toList();

            redisMessages.addAll(0, dbMessages);  // Page 객체에서 실제 메시지 리스트 추가
        }

        return redisMessages;
    }
}
