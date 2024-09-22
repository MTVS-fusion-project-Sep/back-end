package com.mtvs.backend.user;

import com.mtvs.backend.interest.service.InterestCategoryServiceImpl;
import com.mtvs.backend.interest.service.InterestDTOServiceImpl;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserRegisterDTO;
import com.mtvs.backend.user.dto.UserUpdateDTO;
import com.mtvs.backend.user.repository.UserRepository;
import com.mtvs.backend.user.service.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private InterestCategoryServiceImpl interestCategoryServiceImpl;

    @Autowired
    private InterestDTOServiceImpl interestDTOService;

    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();
        entityManager.createNativeQuery("ALTER TABLE user AUTO_INCREMENT = 1").executeUpdate();
        entityManager.flush();

        userServiceImpl.registerUser(new UserRegisterDTO("user1", "1234", "user1", "2001-11-23", "man"));
        userServiceImpl.registerUser(new UserRegisterDTO("user2", "1234", "user2", "2001-11-23", "woman"));
        userServiceImpl.registerUser(new UserRegisterDTO("user3", "1234", "user3", "2001-11-23", "man"));
    }

    @DisplayName("유저 등록 테스트")
    @Test
    @Transactional
    void registerUserTest() {
        Assertions.assertThat(userRepository.count()).isEqualTo(3);
    }
    


    @DisplayName("유저 조회 테스트")
    @Test
    @Transactional
    void findUserTest() {
        Assertions.assertThat(userRepository.findAll().size()).isEqualTo(3);
        Assertions.assertThat(userServiceImpl.getUserByUserId("user1").getUserNickname()).isEqualTo("user1");
        Assertions.assertThat(userServiceImpl.getUserByUserId("user2").getUserNickname()).isEqualTo("user2");
        Assertions.assertThat(userServiceImpl.getUserByUserId("user3").getUserNickname()).isEqualTo("user3");

    }

    @DisplayName("유저 리스트 조회 테스트")
    @Test
    @Transactional
    void findUserListTest() {
        List<User> userList = userServiceImpl.getUserList();

        Assertions.assertThat(userList.size()).isEqualTo(3);
    }

    @DisplayName("유저 정보 수정 테스트")
    @Test
    @Transactional
    void updateUserTest() {
        User foundUser = userServiceImpl.getUserByUserId("user1");
        UserUpdateDTO newUserInfo = new UserUpdateDTO(foundUser.getId(), "user1", "1234", "user11", "2002-11-11", "woman");
        userServiceImpl.updateUser(newUserInfo);

        User foundUser1 = userServiceImpl.getUserByUserId("user1");
        Assertions.assertThat(foundUser1.getUserNickname()).isEqualTo("user11");
        Assertions.assertThat(foundUser1.getBirthday()).isEqualTo("2002-11-11");
        Assertions.assertThat(foundUser1.getGender()).isEqualTo("woman");
    }

    @DisplayName("유저 삭제 테스트")
    @Test
    @Transactional
    void deleteUserTest() {
        Assertions.assertThat(userServiceImpl.getUserList().size()).isEqualTo(3);

        userServiceImpl.deleteUserByUserId("user1");
        Assertions.assertThat(userServiceImpl.getUserList().size()).isEqualTo(2);
        Assertions.assertThat(userServiceImpl.getUserList().get(0).getUserId()).isEqualTo("user2");
        Assertions.assertThat(userServiceImpl.getUserList().get(1).getUserId()).isEqualTo("user3");
    }



}
