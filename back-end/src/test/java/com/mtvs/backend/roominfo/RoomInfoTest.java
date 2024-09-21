package com.mtvs.backend.roominfo;

import com.mtvs.backend.roominfo.dto.RoomInfoRegisterDTO;
import com.mtvs.backend.roominfo.dto.RoomInfoUpdateDTO;
import com.mtvs.backend.roominfo.repository.RoomInfoRepository;
import com.mtvs.backend.roominfo.service.RoomInfoServiceImpl;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.repository.UserRepository;
import com.mtvs.backend.user.service.UserServiceImpl;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RoomInfoTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoomInfoServiceImpl roomInfoServiceImpl;

    @Autowired
    private RoomInfoRepository roomInfoRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private EntityManager em;

    @BeforeEach
    void beforeEach() {
        roomInfoRepository.deleteAll();
        userRepository.deleteAll();

        em.createNativeQuery("ALTER TABLE user AUTO_INCREMENT = 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE room_info AUTO_INCREMENT = 1").executeUpdate();
        em.flush();

        if(userRepository.count() <= 0){
            userServiceImpl.registerUser(new User("user1", "1234", "user1", "2001-11-23", "man"));
            userServiceImpl.registerUser(new User("user2", "1234", "user2", "2001-11-23", "woman"));
            userServiceImpl.registerUser(new User("user3", "1234", "user3", "2001-11-23", "man"));
        }
    }

    @DisplayName("방 정보 등록 테스트")
    @Test
    @Transactional
    void registerRoomInfoTest() {
        roomInfoServiceImpl.registerRoomInfo(new RoomInfoRegisterDTO(1,1, "user1"));
        Assertions.assertThat(roomInfoServiceImpl.getRoomInfoByUserId("user1").getTileIndex()).isEqualTo(1);
    }

    @DisplayName("방 정보 수정 테스트")
    @Test
    @Transactional
    void updateRoomInfoTest() {
        roomInfoServiceImpl.registerRoomInfo(new RoomInfoRegisterDTO(1,1, "user1"));
        Assertions.assertThat(roomInfoServiceImpl.getRoomInfoByUserId("user1").getTileIndex()).isEqualTo(1);
        roomInfoServiceImpl.updateRoomInfo(new RoomInfoUpdateDTO(roomInfoServiceImpl.getRoomInfoByUserId("user1").getId(), 2,2));
        Assertions.assertThat(roomInfoServiceImpl.getRoomInfoByUserId("user1").getTileIndex()).isEqualTo(2);
    }
}
