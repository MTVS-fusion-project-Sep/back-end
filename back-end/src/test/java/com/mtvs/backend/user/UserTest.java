package com.mtvs.backend.user;

import com.mtvs.backend.interest.domain.Interest;
import com.mtvs.backend.interest.domain.InterestCategory;
import com.mtvs.backend.interest.dto.InterestDTO;
import com.mtvs.backend.interest.service.InterestCategoryServiceImpl;
import com.mtvs.backend.interest.service.InterestDTOServiceImpl;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserDTO;
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
    }

    @DisplayName("유저 등록 테스트")
    @Test
    @Transactional
    void registerUserTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        User user2 = new User("user2", "1234", "user2", "2001-02-02", "woman");
        User user3 = new User("user3", "1234", "user3", "2001-03-03", "man");

        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        userServiceImpl.registerUser(new UserDTO(user2.getUserId(), user2.getUserPassword(), user2.getUserNickname(), user2.getBirthday(), user2.getGender()));
        userServiceImpl.registerUser(new UserDTO(user3.getUserId(), user3.getUserPassword(), user3.getUserNickname(), user3.getBirthday(), user3.getGender()));

        Assertions.assertThat(userRepository.count()).isEqualTo(3);
    }
    


    @DisplayName("유저 조회 테스트")
    @Test
    @Transactional
    void findUserTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        User user2 = new User("user2", "1234", "user2", "2001-02-02", "woman");
        User user3 = new User("user3", "1234", "user3", "2001-03-03", "man");

        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        userServiceImpl.registerUser(new UserDTO(user2.getUserId(), user2.getUserPassword(), user2.getUserNickname(), user2.getBirthday(), user2.getGender()));
        userServiceImpl.registerUser(new UserDTO(user3.getUserId(), user3.getUserPassword(), user3.getUserNickname(), user3.getBirthday(), user3.getGender()));

        Assertions.assertThat(userRepository.findAll().size()).isEqualTo(3);
        Assertions.assertThat(userServiceImpl.getUserByUserId("user1").getUserNickname()).isEqualTo("user1");
        Assertions.assertThat(userServiceImpl.getUserByUserId("user2").getUserNickname()).isEqualTo("user2");
        Assertions.assertThat(userServiceImpl.getUserByUserId("user3").getUserNickname()).isEqualTo("user3");

    }

    @DisplayName("유저 리스트 조회 테스트")
    @Test
    @Transactional
    void findUserListTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        User user2 = new User("user2", "1234", "user2", "2001-02-02", "woman");
        User user3 = new User("user3", "1234", "user3", "2001-03-03", "man");

        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        userServiceImpl.registerUser(new UserDTO(user2.getUserId(), user2.getUserPassword(), user2.getUserNickname(), user2.getBirthday(), user2.getGender()));
        userServiceImpl.registerUser(new UserDTO(user3.getUserId(), user3.getUserPassword(), user3.getUserNickname(), user3.getBirthday(), user3.getGender()));

        List<User> userList = userServiceImpl.getUserList();

        Assertions.assertThat(userList.size()).isEqualTo(3);
    }

    @DisplayName("유저 정보 수정 테스트")
    @Test
    @Transactional
    void updateUserTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        User user2 = new User("user2", "1234", "user2", "2001-02-02", "woman");
        User user3 = new User("user3", "1234", "user3", "2001-03-03", "man");

        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        userServiceImpl.registerUser(new UserDTO(user2.getUserId(), user2.getUserPassword(), user2.getUserNickname(), user2.getBirthday(), user2.getGender()));
        userServiceImpl.registerUser(new UserDTO(user3.getUserId(), user3.getUserPassword(), user3.getUserNickname(), user3.getBirthday(), user3.getGender()));

        UserDTO newUserInfo = new UserDTO("user1", "1234", "user11", "2002-11-11", "woman");
        User foundUser1 = userServiceImpl.getUserByUserId("user1");
        userServiceImpl.updateUser(foundUser1.getId(), newUserInfo);

        User foundUser = userServiceImpl.getUserByUserId("user1");
        Assertions.assertThat(foundUser.getUserNickname()).isEqualTo("user11");
        Assertions.assertThat(foundUser.getBirthday()).isEqualTo("2002-11-11");
        Assertions.assertThat(foundUser.getGender()).isEqualTo("woman");
    }

    @DisplayName("유저 삭제 테스트")
    @Test
    @Transactional
    void deleteUserTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        User user2 = new User("user2", "1234", "user2", "2001-02-02", "woman");
        User user3 = new User("user3", "1234", "user3", "2001-03-03", "man");

        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        userServiceImpl.registerUser(new UserDTO(user2.getUserId(), user2.getUserPassword(), user2.getUserNickname(), user2.getBirthday(), user2.getGender()));
        userServiceImpl.registerUser(new UserDTO(user3.getUserId(), user3.getUserPassword(), user3.getUserNickname(), user3.getBirthday(), user3.getGender()));

        Assertions.assertThat(userServiceImpl.getUserList().size()).isEqualTo(3);

        userServiceImpl.deleteUserByUserId("user1");
        Assertions.assertThat(userServiceImpl.getUserList().size()).isEqualTo(2);
        Assertions.assertThat(userServiceImpl.getUserList().get(0).getUserId()).isEqualTo("user2");
        Assertions.assertThat(userServiceImpl.getUserList().get(1).getUserId()).isEqualTo("user3");
    }



}
