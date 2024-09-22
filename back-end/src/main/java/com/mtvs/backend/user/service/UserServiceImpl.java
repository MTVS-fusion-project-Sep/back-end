package com.mtvs.backend.user.service;

import com.mtvs.backend.interest.domain.Interest;
import com.mtvs.backend.interest.domain.InterestCategory;
import com.mtvs.backend.interest.dto.InterestDTO;
import com.mtvs.backend.interest.service.InterestCategoryServiceImpl;
import com.mtvs.backend.interest.service.InterestDTOServiceImpl;
import com.mtvs.backend.interest.service.InterestServiceImpl;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserRegisterDTO;
import com.mtvs.backend.user.dto.UserUpdateDTO;
import com.mtvs.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final InterestDTOServiceImpl interestDTOServiceImpl;
    private final InterestServiceImpl interestServiceImpl;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, InterestDTOServiceImpl interestDTOServiceImpl, InterestServiceImpl interestServiceImpl) {
        this.userRepository = userRepository;
        this.interestDTOServiceImpl = interestDTOServiceImpl;
        this.interestServiceImpl = interestServiceImpl;
    }

    public void registerUser(UserRegisterDTO urDTO) {
        User newUser = new User(urDTO.getUserId(), urDTO.getUserPassword(), urDTO.getUserNickname(), urDTO.getBirthday(), urDTO.getGender());
        userRepository.save(newUser);
    }

    @Override
    public void updateInterestList(String userId, List<InterestDTO> interestDTOList) {
        User user = userRepository.findByUserId(userId);
        List<Interest> userInterestList = user.getInterestList();
        List<Interest> newInterests = new ArrayList<>();
        for (InterestDTO interestDTO : interestDTOList) {
            // 만약 주어진 InterestCategory 또는 Interest가 DB에 저장되어있지 않으면 저장하는 메소드임.
            interestDTOServiceImpl.createInterestDTO(interestDTO);
            Interest foundInterest = interestServiceImpl.getInterestByInterestCategoryNameAndInterestName(interestDTO);
            newInterests.add(foundInterest);
        }

        // 유저의 기존 관심사 중에서 interestListToRemove에 없는 것은 삭제
        List<Interest> interestListToRemove = new ArrayList<>();
        for (Interest existingInterest : userInterestList) {
            if (!newInterests.contains(existingInterest)) {
                interestListToRemove.add(existingInterest);
            }
        }

        // removeAll 함수는 Java에서 제공하는 Collection 인터페이스의 메소드로, 주어진 컬렉션에 포함된 모든 요소들을 현재 컬렉션에서 제거합니다
        userInterestList.removeAll(interestListToRemove);

        // 새로운 관심사를 유저의 관심사 리스트에 추가
        for (Interest newInterest : newInterests) {
            if (!userInterestList.contains(newInterest)) {
                userInterestList.add(newInterest);
            }
        }

        user.setInterestList(userInterestList);
        userRepository.save(user);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("not found user id: " + userId));
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(UserUpdateDTO userDTO) {
        User user = getUserById(userDTO.getId());
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
