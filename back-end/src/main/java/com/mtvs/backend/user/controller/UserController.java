package com.mtvs.backend.user.controller;

import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserDTO;
import com.mtvs.backend.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        UserDTO userDTO = new UserDTO(user.getUserId(), user.getUserPassword(), user.getUserNickname(), user.getBirthday(), user.getGender());
        userServiceImpl.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        User foundUser = userServiceImpl.getUserByUserId(userId);
        return ResponseEntity.ok(foundUser);
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User foundUser = userServiceImpl.getUserById(user.getId());
        foundUser.setUserId(user.getUserId());
        foundUser.setUserPassword(user.getUserPassword());
        foundUser.setUserNickname(user.getUserNickname());
        foundUser.setBirthday(user.getBirthday());
        foundUser.setGender(user.getGender());
        UserDTO userDTO = new UserDTO(foundUser.getUserId(), foundUser.getUserPassword(), foundUser.getUserNickname(), foundUser.getBirthday(), foundUser.getGender());
        userServiceImpl.updateUser(user.getId(), userDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") String userId){
        userServiceImpl.deleteUserByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
