package com.mtvs.backend.user.service;

import com.mtvs.backend.interest.domain.Interest;
import com.mtvs.backend.interest.dto.InterestDTO;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserDTO;
import com.mtvs.backend.user.repository.UserRepository;

import java.util.List;

public interface UserService{
    void registerUser(UserDTO userDTO);
    void updateInterestList(String userId, List<InterestDTO> interestDTOList);
    User getUserByUserId(String userId);
    User getUserById(Long userId);
    List<User> getUserList();
    void updateUser(long userId, UserDTO userDTO);
    void deleteUserByUserId(String userId);
}
