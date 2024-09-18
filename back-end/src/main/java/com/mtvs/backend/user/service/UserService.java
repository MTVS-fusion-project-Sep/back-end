package com.mtvs.backend.user.service;

import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserDTO;
import com.mtvs.backend.user.repository.UserRepository;

import java.util.List;

public interface UserService{
    void registerUser(UserDTO userDTO);
    User getUserByUserId(String userId);
    List<User> getUserList();
    void updateUser(String userId, UserDTO userDTO);
    void deleteUserByUserId(String userId);
}
