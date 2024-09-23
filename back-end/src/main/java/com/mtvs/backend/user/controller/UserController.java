package com.mtvs.backend.user.controller;

import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserRegisterDTO;
import com.mtvs.backend.user.dto.UserUpdateDTO;
import com.mtvs.backend.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<UserRegisterDTO> registerUser(@RequestBody List<UserRegisterDTO> urDTO) {
        userServiceImpl.registerUser(urDTO.get(0));
        return ResponseEntity.status(HttpStatus.CREATED).body(urDTO.get(0));
    }

    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam("userId") String userId){
        User foundUser = userServiceImpl.getUserByUserId(userId);
        return ResponseEntity.ok(foundUser);
    }

    @PatchMapping
    public ResponseEntity<UserUpdateDTO> updateUser(@RequestBody UserUpdateDTO uuDTO){
        userServiceImpl.updateUser(uuDTO);
        return ResponseEntity.ok(uuDTO);
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestParam("userId") String userId){
        userServiceImpl.deleteUserByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
