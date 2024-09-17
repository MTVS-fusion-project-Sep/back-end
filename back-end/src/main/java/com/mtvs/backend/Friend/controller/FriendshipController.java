package com.mtvs.backend.Friend.controller;

import com.mtvs.backend.friend.service.FriendshipService;
import com.mtvs.backend.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @Autowired
    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }
    
    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestParam Long requesterId, @RequestParam Long receiverId) {
        friendshipService.sendFriendRequest(requesterId, receiverId);
        return ResponseEntity.ok("友達リクエストを送りました。");
    }
    
    @PostMapping("/accept")
    public ResponseEntity<String> acceptFriendRequest(@RequestParam Long friendshipId) {
        friendshipService.acceptFriendRequest(friendshipId);
        return ResponseEntity.ok("友人のリクエストを受け入れました。");
    }
    
    @PostMapping("/reject")
    public ResponseEntity<String> rejectFriendRequest(@RequestParam Long friendshipId) {
        friendshipService.rejectFriendRequest(friendshipId);
        return ResponseEntity.ok("友達のリクエストを断りました。");
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<User>> getFriends(@RequestParam Long userId) {
        List<User> friends = friendshipService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }
}

