package com.mtvs.backend.roominfo;

import com.mtvs.backend.roominfo.dto.RoomInfoRegisterDTO;
import com.mtvs.backend.roominfo.dto.RoomInfoUpdateDTO;
import com.mtvs.backend.roominfo.service.RoomInfoServiceImpl;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
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

    @DisplayName("방 정보 등록 테스트")
    @Test
    @Transactional
    void registerRoomInfoTest() {
        roomInfoServiceImpl.registerRoomInfo(new RoomInfoRegisterDTO("wp1", "tn1", "user1"));
        Assertions.assertThat(roomInfoServiceImpl.getRoomInfoByUserId("user1").getTileName()).isEqualTo("tn1");
    }

    @DisplayName("방 정보 수정 테스트")
    @Test
    @Transactional
    void updateRoomInfoTest() {
        roomInfoServiceImpl.registerRoomInfo(new RoomInfoRegisterDTO("wp1", "tn1", "user1"));
        Assertions.assertThat(roomInfoServiceImpl.getRoomInfoByUserId("user1").getTileName()).isEqualTo("tn1");
        roomInfoServiceImpl.updateRoomInfo(new RoomInfoUpdateDTO(roomInfoServiceImpl.getRoomInfoByUserId("user1").getId(), "wp2", "tn2"));
        Assertions.assertThat(roomInfoServiceImpl.getRoomInfoByUserId("user1").getTileName()).isEqualTo("tn2");
    }
}
