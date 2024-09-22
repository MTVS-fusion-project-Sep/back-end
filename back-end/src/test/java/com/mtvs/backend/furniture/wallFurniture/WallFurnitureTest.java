package com.mtvs.backend.furniture.wallFurniture;

import com.mtvs.backend.furniture.wallfurniture.domain.WallFurniPosType;
import com.mtvs.backend.furniture.wallfurniture.domain.WallFurniture;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureRegisterDTO;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureUpdateDTO;
import com.mtvs.backend.furniture.wallfurniture.repository.WallFurnitureRepository;
import com.mtvs.backend.furniture.wallfurniture.service.WallFurnitureServiceImpl;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserRegisterDTO;
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
public class WallFurnitureTest {

    @Autowired
    private WallFurnitureServiceImpl wallFurnitureServiceImpl;

    @Autowired
    private WallFurnitureRepository wallFurnitureRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();
        wallFurnitureRepository.deleteAll();

        em.createNativeQuery("ALTER TABLE user AUTO_INCREMENT = 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE wall_furniture AUTO_INCREMENT = 1").executeUpdate();
        em.flush();

        if(userRepository.count() <= 0){
            userServiceImpl.registerUser(new UserRegisterDTO("user1", "1234", "user1", "2001-11-23", "man"));
            userServiceImpl.registerUser(new UserRegisterDTO("user2", "1234", "user2", "2001-11-23", "woman"));
            userServiceImpl.registerUser(new UserRegisterDTO("user3", "1234", "user3", "2001-11-23", "man"));
        }

        if(wallFurnitureRepository.count() <= 0){
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Shelf", "Bookshelf", WallFurniPosType.NONE, true, "user1"));
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Cabinet", "Kitchen Cabinet", WallFurniPosType.RIGHT_TWO, false, "user1"));
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Mirror", "Wall Mirror", WallFurniPosType.RIGHT_ONE, true, "user1"));
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Artwork", "Painting", WallFurniPosType.LEFT_TWO, true, "user1"));
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Rack", "Coat Rack", WallFurniPosType.RIGHT_TWO, false, "user2"));
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Shelf", "Floating Shelf", WallFurniPosType.NONE, true, "user2"));
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Cabinet", "Bathroom Cabinet", WallFurniPosType.LEFT_TWO, false,"user2"));
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Artwork", "Wall Art", WallFurniPosType.LEFT_TWO, true,"user3"));
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Mirror", "Full-Length Mirror", WallFurniPosType.RIGHT_ONE, true,"user3"));
            wallFurnitureServiceImpl.registerWallFurniture(new WallFurnitureRegisterDTO("Rack", "Shoe Rack", WallFurniPosType.NONE, true,"user3"));
        }
    }

    @DisplayName("벽 가구 조회 테스트")
    @Test
    @Transactional
    void findWallFurntiureTest() {
        List<WallFurniture> wallFurnitureList = wallFurnitureServiceImpl.getAllWallFurniture();
        Assertions.assertThat(wallFurnitureList.size()).isEqualTo(10);
    }

    @DisplayName("유저 아이디로 벽 가구 조회 테스트")
    @Test
    @Transactional
    void findWallFurnitureByUserIdTest() {

        List<WallFurniture> wallFurnitureList1 = wallFurnitureServiceImpl.getAllWallFurniture();
        Assertions.assertThat(wallFurnitureList1.size()).isEqualTo(10);

        List<WallFurniture> wallFurnitureList = wallFurnitureServiceImpl.getWallFurnitureListByUserId("user1");
        Assertions.assertThat(wallFurnitureList.size()).isEqualTo(4);
    }

    @DisplayName("유저 아이디로 배치된 벽 가구들 조회 테스트")
    @Test
    @Transactional
    void findWallFurnitureListByUserIdAndFurniOnPlaceTest() {
        List<WallFurniture> wallFurnitureList = wallFurnitureServiceImpl.getWallFurnitureListByUserIdAndOnPlace("user1", true);
        Assertions.assertThat(wallFurnitureList.size()).isEqualTo(3);
    }

    @DisplayName("벽 가구 수정 테스트")
    @Test
    @Transactional
    void updateWallFurnitureTest() {
        List<WallFurniture> allWallFurnitureList = wallFurnitureServiceImpl.getAllWallFurniture();
        WallFurniture lastWallFurniture = allWallFurnitureList.get(0);
        WallFurnitureUpdateDTO wfuDTO = new WallFurnitureUpdateDTO(lastWallFurniture.getId(), "newCategory1", "newFurniName1", WallFurniPosType.NONE, true);
        wallFurnitureServiceImpl.updateWallFurniture(wfuDTO);
        Assertions.assertThat(wallFurnitureServiceImpl.getAllWallFurniture().get(0).getFurniCategory()).isEqualTo("newCategory1");
    }

    @DisplayName("벽 가구 제거 테스트")
    @Test
    @Transactional
    void deleteWallFurnitureTest() {
        String tmp = wallFurnitureServiceImpl.getAllWallFurniture().get(0).getFurniName();
        wallFurnitureServiceImpl.deleteWallFurnitureById(wallFurnitureServiceImpl.getAllWallFurniture().get(0).getId());
        Assertions.assertThat(wallFurnitureServiceImpl.getAllWallFurniture().size()).isEqualTo(9);
        Assertions.assertThat(wallFurnitureServiceImpl.getWallFurnitureByName(tmp)).isNull();
    }
}
