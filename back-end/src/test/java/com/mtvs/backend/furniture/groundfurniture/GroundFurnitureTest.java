package com.mtvs.backend.furniture.groundfurniture;

import com.mtvs.backend.furniture.groundfurniture.domain.GroundFurniture;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureRegisterDTO;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureUpdateDTO;
import com.mtvs.backend.furniture.groundfurniture.service.GroundFurnitureServiceImpl;
import com.mtvs.backend.user.dto.UserDTO;
import com.mtvs.backend.user.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class GroundFurnitureTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private GroundFurnitureServiceImpl groundFurnitureServiceImpl;

    @DisplayName("가구 등록 테스트")
    @Test
    @Transactional
    void registerFurnitureTest() {

        userServiceImpl.registerUser(new UserDTO("user1", "1234", "user1", "2001-11-23", "man"));
        userServiceImpl.registerUser(new UserDTO("user2", "1234", "user2", "2001-11-23", "man"));
        userServiceImpl.registerUser(new UserDTO("user3", "1234", "user3", "2001-11-23", "man"));
        userServiceImpl.registerUser(new UserDTO("user4", "1234", "user4", "2001-11-23", "man"));
        userServiceImpl.registerUser(new UserDTO("user5", "1234", "user5", "2001-11-23", "man"));

        // 임의의 값으로 GroundFurnitureRegisterDTO 객체 10개 생성
        GroundFurnitureRegisterDTO furniture1 = new GroundFurnitureRegisterDTO(100, 200, true, 10, 20, false, "Living Room", "Sofa", "user1");
        GroundFurnitureRegisterDTO furniture2 = new GroundFurnitureRegisterDTO(150, 250, false, 30, 40, true, "Bedroom", "Bed", "user1");
        GroundFurnitureRegisterDTO furniture3 = new GroundFurnitureRegisterDTO(120, 220, true, 15, 25, true, "Office", "Desk", "user1");
        GroundFurnitureRegisterDTO furniture4 = new GroundFurnitureRegisterDTO(110, 210, false, 20, 30, false, "Kitchen", "Table", "user2");
        GroundFurnitureRegisterDTO furniture5 = new GroundFurnitureRegisterDTO(130, 230, true, 12, 22, true, "Living Room", "Chair", "user2");
        GroundFurnitureRegisterDTO furniture6 = new GroundFurnitureRegisterDTO(140, 240, false, 18, 28, false, "Bedroom", "Wardrobe", "user2");
        GroundFurnitureRegisterDTO furniture7 = new GroundFurnitureRegisterDTO(125, 225, true, 11, 21, true, "Office", "Bookshelf", "user2");
        GroundFurnitureRegisterDTO furniture8 = new GroundFurnitureRegisterDTO(160, 260, false, 35, 45, false, "Kitchen", "Stool", "user3");
        GroundFurnitureRegisterDTO furniture9 = new GroundFurnitureRegisterDTO(135, 235, true, 17, 27, true, "Living Room", "TV Stand", "user4");
        GroundFurnitureRegisterDTO furniture10 = new GroundFurnitureRegisterDTO(170, 270, false, 40, 50, false, "Bedroom", "Nightstand", "user5");

        groundFurnitureServiceImpl.registerGroundFurniture(furniture1);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture2);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture3);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture4);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture5);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture6);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture7);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture8);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture9);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture10);

        List<GroundFurniture> allGroundFurniture = groundFurnitureServiceImpl.getAllGroundFurniture();
        List<GroundFurniture> groundFurnitureList_user1 = groundFurnitureServiceImpl.getGroundFurnitureListByUserId("user1");

        Assertions.assertThat(allGroundFurniture.size()).isEqualTo(10);
        Assertions.assertThat(groundFurnitureList_user1.size()).isEqualTo(3);
    }

    @DisplayName("가구 수정 테스트")
    @Test
    @Transactional
    void updateFurnitureTest() {

        userServiceImpl.registerUser(new UserDTO("user1", "1234", "user1", "2001-11-23", "man"));
        userServiceImpl.registerUser(new UserDTO("user2", "1234", "user2", "2001-11-23", "man"));

        // 임의의 값으로 GroundFurnitureRegisterDTO 객체 10개 생성
        GroundFurnitureRegisterDTO furniture1 = new GroundFurnitureRegisterDTO(100, 200, true, 10, 20, false, "Living Room", "Sofa", "user1");
        GroundFurnitureRegisterDTO furniture2 = new GroundFurnitureRegisterDTO(150, 250, false, 30, 40, true, "Bedroom", "Bed", "user1");
        GroundFurnitureRegisterDTO furniture3 = new GroundFurnitureRegisterDTO(120, 220, true, 15, 25, true, "Office", "Desk", "user1");
        GroundFurnitureRegisterDTO furniture4 = new GroundFurnitureRegisterDTO(110, 210, false, 20, 30, false, "Kitchen", "Table", "user2");

        groundFurnitureServiceImpl.registerGroundFurniture(furniture1);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture2);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture3);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture4);

        Long tmp = groundFurnitureServiceImpl.getGroundFurnitureByName("Sofa").getId();

        groundFurnitureServiceImpl.updateGroundFurniture(new GroundFurnitureUpdateDTO(tmp,200, 300, true, 20,30, false, "Bedroom", "Bed"));

        Assertions.assertThat(groundFurnitureServiceImpl.getGroundFurnitureById(tmp).getFurniCategory()).isEqualTo("Bedroom");
    }

    @DisplayName("가구 삭제 테스트")
    @Test
    @Transactional
    void deleteFurnitureTest() {
        userServiceImpl.registerUser(new UserDTO("user1", "1234", "user1", "2001-11-23", "man"));
        userServiceImpl.registerUser(new UserDTO("user2", "1234", "user2", "2001-11-23", "man"));

        // 임의의 값으로 GroundFurnitureRegisterDTO 객체 10개 생성
        GroundFurnitureRegisterDTO furniture1 = new GroundFurnitureRegisterDTO(100, 200, true, 10, 20, false, "Living Room", "Sofa", "user1");
        GroundFurnitureRegisterDTO furniture2 = new GroundFurnitureRegisterDTO(150, 250, false, 30, 40, true, "Bedroom", "Bed", "user1");
        GroundFurnitureRegisterDTO furniture3 = new GroundFurnitureRegisterDTO(120, 220, true, 15, 25, true, "Office", "Desk", "user1");
        GroundFurnitureRegisterDTO furniture4 = new GroundFurnitureRegisterDTO(110, 210, false, 20, 30, false, "Kitchen", "Table", "user2");

        groundFurnitureServiceImpl.registerGroundFurniture(furniture1);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture2);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture3);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture4);

        Long tmp = groundFurnitureServiceImpl.getGroundFurnitureByName("Sofa").getId();

        groundFurnitureServiceImpl.deleteGroundFurnitureById(tmp);

        List<GroundFurniture> allGroundFurniture = groundFurnitureServiceImpl.getAllGroundFurniture();

        Assertions.assertThat(allGroundFurniture.size()).isEqualTo(3);
        Assertions.assertThat(groundFurnitureServiceImpl.getGroundFurnitureById(tmp)).isNull();
    }

    @DisplayName("유저 이름으로 가구 조회 테스트")
    @Test
    @Transactional
    void findFurnitureListTest() {
        userServiceImpl.registerUser(new UserDTO("user1", "1234", "user1", "2001-11-23", "man"));
        userServiceImpl.registerUser(new UserDTO("user2", "1234", "user2", "2001-11-23", "man"));

        // 임의의 값으로 GroundFurnitureRegisterDTO 객체 10개 생성
        GroundFurnitureRegisterDTO furniture1 = new GroundFurnitureRegisterDTO(100, 200, true, 10, 20, false, "Living Room", "Sofa", "user1");
        GroundFurnitureRegisterDTO furniture2 = new GroundFurnitureRegisterDTO(150, 250, false, 30, 40, true, "Bedroom", "Bed", "user1");
        GroundFurnitureRegisterDTO furniture3 = new GroundFurnitureRegisterDTO(120, 220, true, 15, 25, true, "Office", "Desk", "user1");
        GroundFurnitureRegisterDTO furniture4 = new GroundFurnitureRegisterDTO(110, 210, false, 20, 30, false, "Kitchen", "Table", "user2");

        groundFurnitureServiceImpl.registerGroundFurniture(furniture1);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture2);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture3);
        groundFurnitureServiceImpl.registerGroundFurniture(furniture4);

        List<GroundFurniture> gfList1 = groundFurnitureServiceImpl.getGroundFurnitureListByUserId("user1");

        Assertions.assertThat(gfList1.size()).isEqualTo(3);
    }
}