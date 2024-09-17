package com.mtvs.backend.user.controller;

import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserDTO;
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
    public ResponseEntity<User> registerUser(@RequestBody List<UserDTO> userDTO) {
        User newUser = new User(userDTO.get(0).getUserId(), userDTO.get(0).getUserPassword(), userDTO.get(0).getUserNickname(), userDTO.get(0).getBirthday(), userDTO.get(0).getGender());
        userServiceImpl.registerUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        User foundUser = userServiceImpl.getUserByUserId(userId);
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping("/temp")
    public String getTempUser(){
        return "success";
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
