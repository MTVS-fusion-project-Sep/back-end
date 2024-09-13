package com.mtvs.backend.friend.service;

import com.mtvs.backend.friend.domain.Friendship;
import com.mtvs.backend.user.domain.User;

import java.util.List;

public interface FriendshipService {
    void sendFriendRequest(Long requesterId, Long receiverId); // 친구 요청
    void acceptFriendRequest(Long friendshipId); // 친구 수락
    void rejectFriendRequest(Long friendshipId); // 친구 요청 거절
    List<User> getFriends(Long userId); // 친구 목록 조회
    List<Friendship> getAllFriendship(Boolean isAccepted); // isAccepted값에 따른 모든 Friendship 조회 
}
