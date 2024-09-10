package com.mtvs.backend.user.service;

import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserDTO;
import com.mtvs.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserNickname(userDTO.getUserNickname());
        user.setBirthday(userDTO.getBirthday());
        user.setGender(userDTO.getGender());

        userRepository.save(user);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        User user = getUserByUserId(userId);

        user.setUserId(userDTO.getUserId());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserNickname(userDTO.getUserNickname());
        user.setBirthday(userDTO.getBirthday());
        user.setGender(userDTO.getGender());

        userRepository.save(user);
    }

    @Override
    public void deleteUserByUserId(String userId) {
        User user = getUserByUserId(userId);
        userRepository.deleteById(user.getId());
    }
}
