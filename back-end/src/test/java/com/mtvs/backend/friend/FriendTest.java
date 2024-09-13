package com.mtvs.backend.friend;

import com.mtvs.backend.friend.domain.Friendship;
import com.mtvs.backend.friend.service.FriendshipServiceImpl;
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

import java.util.List;

@SpringBootTest
public class FriendTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FriendshipServiceImpl friendshipService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private FriendshipServiceImpl friendshipServiceImpl;

    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();
        entityManager.createNativeQuery("ALTER TABLE user AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE friendship AUTO_INCREMENT = 1").executeUpdate();
        entityManager.flush();
    }

    @DisplayName("친구 요청 테스트")
    @Test
    @Transactional
    void sendFriendRequestTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        User user2 = new User("user2", "1234", "user2", "2001-02-02", "woman");
        User user3 = new User("user3", "1234", "user3", "2001-03-03", "man");

        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        userServiceImpl.registerUser(new UserDTO(user2.getUserId(), user2.getUserPassword(), user2.getUserNickname(), user2.getBirthday(), user2.getGender()));
        userServiceImpl.registerUser(new UserDTO(user3.getUserId(), user3.getUserPassword(), user3.getUserNickname(), user3.getBirthday(), user3.getGender()));

        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user1").getId(), userServiceImpl.getUserByUserId("user2").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user2").getId(), userServiceImpl.getUserByUserId("user3").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user3").getId(), userServiceImpl.getUserByUserId("user1").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user2").getId(), userServiceImpl.getUserByUserId("user1").getId());

        List<Friendship> allFriendship = friendshipServiceImpl.getAllFriendship(false);
        Assertions.assertThat(allFriendship.size()).isEqualTo(4);
    }

    @DisplayName("친구 요청 수락 테스트")
    @Test
    @Transactional
    void acceptFriendRequestTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        User user2 = new User("user2", "1234", "user2", "2001-02-02", "woman");
        User user3 = new User("user3", "1234", "user3", "2001-03-03", "man");

        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        userServiceImpl.registerUser(new UserDTO(user2.getUserId(), user2.getUserPassword(), user2.getUserNickname(), user2.getBirthday(), user2.getGender()));
        userServiceImpl.registerUser(new UserDTO(user3.getUserId(), user3.getUserPassword(), user3.getUserNickname(), user3.getBirthday(), user3.getGender()));

        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user1").getId(), userServiceImpl.getUserByUserId("user2").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user2").getId(), userServiceImpl.getUserByUserId("user3").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user3").getId(), userServiceImpl.getUserByUserId("user1").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user2").getId(), userServiceImpl.getUserByUserId("user1").getId());

        List<Friendship> friendshipList = friendshipServiceImpl.getAllFriendship(false);

        for(Friendship f: friendshipList){
            friendshipServiceImpl.acceptFriendRequest(f.getId());
        }

        List<User> friendList = friendshipServiceImpl.getFriends(1L);

        Assertions.assertThat(friendList.size()).isEqualTo(2);
        Assertions.assertThat(friendList.get(0).getUserId()).isEqualTo("user3");
        Assertions.assertThat(friendList.get(1).getUserId()).isEqualTo("user2");
    }

    @DisplayName("친구 요청 거절 테스트")
    @Test
    @Transactional
    void rejectFriendRequestTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        User user2 = new User("user2", "1234", "user2", "2001-02-02", "woman");
        User user3 = new User("user3", "1234", "user3", "2001-03-03", "man");

        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        userServiceImpl.registerUser(new UserDTO(user2.getUserId(), user2.getUserPassword(), user2.getUserNickname(), user2.getBirthday(), user2.getGender()));
        userServiceImpl.registerUser(new UserDTO(user3.getUserId(), user3.getUserPassword(), user3.getUserNickname(), user3.getBirthday(), user3.getGender()));

        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user1").getId(), userServiceImpl.getUserByUserId("user2").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user2").getId(), userServiceImpl.getUserByUserId("user3").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user3").getId(), userServiceImpl.getUserByUserId("user1").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user2").getId(), userServiceImpl.getUserByUserId("user1").getId());

        friendshipServiceImpl.rejectFriendRequest(1L);

        Assertions.assertThat(friendshipServiceImpl.getFriends(1L)).isEmpty();
        Assertions.assertThat(friendshipServiceImpl.getAllFriendship(false).size()).isEqualTo(3);
    }

    @DisplayName("친구 리스트 조회 테스트")
    @Test
    @Transactional
    void findFriendsTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        User user2 = new User("user2", "1234", "user2", "2001-02-02", "woman");
        User user3 = new User("user3", "1234", "user3", "2001-03-03", "man");

        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        userServiceImpl.registerUser(new UserDTO(user2.getUserId(), user2.getUserPassword(), user2.getUserNickname(), user2.getBirthday(), user2.getGender()));
        userServiceImpl.registerUser(new UserDTO(user3.getUserId(), user3.getUserPassword(), user3.getUserNickname(), user3.getBirthday(), user3.getGender()));

        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user1").getId(), userServiceImpl.getUserByUserId("user2").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user2").getId(), userServiceImpl.getUserByUserId("user3").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user3").getId(), userServiceImpl.getUserByUserId("user1").getId());
        friendshipServiceImpl.sendFriendRequest(userServiceImpl.getUserByUserId("user2").getId(), userServiceImpl.getUserByUserId("user1").getId());

        List<Friendship> friendshipList = friendshipServiceImpl.getAllFriendship(false);

        for(Friendship f: friendshipList){
            f.setAccepted(true);
        }

        List<User> friendList = friendshipServiceImpl.getFriends(userServiceImpl.getUserByUserId("user1").getId());

        Assertions.assertThat(friendList.size()).isEqualTo(2);
    }


}
