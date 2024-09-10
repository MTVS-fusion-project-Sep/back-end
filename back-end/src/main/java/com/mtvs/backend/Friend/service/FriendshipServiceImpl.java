package com.mtvs.backend.Friend.service;

import com.mtvs.backend.Friend.repository.FriendshipRepository;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mtvs.backend.Friend.domain.Friendship;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipServiceImpl implements FriendshipService{

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void sendFriendRequest(Long requesterId, Long receiverId) {
        User requester = userRepository.findById(requesterId).orElseThrow(() -> new IllegalArgumentException("Requester not found"));
        User receiver = userRepository.findById(receiverId).orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        Friendship friendship = new Friendship();
        friendship.setRequester(requester);
        friendship.setReceiver(receiver);
        friendship.setAccepted(false);

        friendshipRepository.save(friendship);
    }

    @Override
    public void acceptFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("Friendship not found"));

        friendship.setAccepted(true);
        friendshipRepository.save(friendship);
    }

    @Override
    public void rejectFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new IllegalArgumentException("Friendship not found"));

        friendshipRepository.delete(friendship);
    }

    @Override
    public List<User> getFriends(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Friendship> friendshipList = friendshipRepository.findByReceiverAndIsAccepted(user, true);

        return friendshipList.stream()
                .map(Friendship::getRequester)
                .collect(Collectors.toList());
    }

    @Override
    public List<Friendship> getAllFriendship(Boolean isAccepted) {
        return friendshipRepository.findAllFriendship(isAccepted);
    }
}
