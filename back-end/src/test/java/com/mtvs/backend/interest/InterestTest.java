package com.mtvs.backend.interest;

import com.mtvs.backend.interest.domain.Interest;
import com.mtvs.backend.interest.domain.InterestCategory;
import com.mtvs.backend.interest.dto.InterestDTO;
import com.mtvs.backend.interest.repository.InterestCategoryRepository;
import com.mtvs.backend.interest.repository.InterestRepository;
import com.mtvs.backend.interest.service.InterestCategoryServiceImpl;
import com.mtvs.backend.interest.service.InterestDTOServiceImpl;
import com.mtvs.backend.interest.service.InterestServiceImpl;
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
public class InterestTest {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    InterestCategoryRepository interestCategoryRepository;

    @Autowired
    private InterestDTOServiceImpl interestDTOService;
    @Autowired
    private InterestCategoryServiceImpl interestCategoryServiceImpl;
    @Autowired
    EntityManager entityManager;
    @Autowired
    private InterestServiceImpl interestServiceImpl;

    @BeforeEach
    void beforeEach() {
        interestRepository.deleteAll();
        interestCategoryRepository.deleteAll();
        entityManager.createNativeQuery("ALTER TABLE interest AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE interest_category AUTO_INCREMENT = 1").executeUpdate();
        entityManager.flush();

        InterestDTO interestDTOList[] = {
                new InterestDTO("게임", "롤"),
                new InterestDTO("게임", "오버워치"),
                new InterestDTO("게임", "FIFA"),
                new InterestDTO("게임", "Game&Watch"),
                new InterestDTO("금융", "주식"),
                new InterestDTO("금융", "부동산"),
                new InterestDTO("금융", "재테크"),
                new InterestDTO("음악", "K-POP"),
                new InterestDTO("음악", "발라드"),
                new InterestDTO("음악", "밴드"),
                new InterestDTO("음악", "락"),
                new InterestDTO("생활/취미", "인테리어"),
                new InterestDTO("생활/취미", "자동차"),
                new InterestDTO("생활/취미", "요리"),
                new InterestDTO("생활/취미", "거지방ㅋ")
        };

        for(InterestDTO interestDTO : interestDTOList){
            interestDTOService.createInterestDTO(interestDTO);
        }
    }

    @DisplayName("유저 관심사 수정 테스트")
    @Test
    @Transactional
    void updateInterestTest() {
        User user1 = new User("user1", "1234", "user1", "2001-01-01", "man");
        userServiceImpl.registerUser(new UserDTO(user1.getUserId(), user1.getUserPassword(), user1.getUserNickname(), user1.getBirthday(), user1.getGender()));
        User foundUser = userServiceImpl.getUserByUserId("user1");

        List<InterestDTO> selectedInterestDtoList = new ArrayList<>();
        selectedInterestDtoList.add(new InterestDTO("게임", "롤"));
        selectedInterestDtoList.add(new InterestDTO("게임", "오버워치"));
        selectedInterestDtoList.add(new InterestDTO("금융", "주식"));
        selectedInterestDtoList.add(new InterestDTO("금융", "부동산"));
        selectedInterestDtoList.add(new InterestDTO("음악", "발라드"));
        selectedInterestDtoList.add(new InterestDTO("음악", "밴드"));
        selectedInterestDtoList.add(new InterestDTO("생활/취미", "인테리어"));
        selectedInterestDtoList.add(new InterestDTO("생활/취미", "거지방ㅋ"));

        userServiceImpl.updateInterestList(user1.getUserId(), selectedInterestDtoList);
        User foundUser1 = userServiceImpl.getUserByUserId("user1");
        Assertions.assertThat(foundUser1.getInterestList().size()).isEqualTo(8);
    }


    @DisplayName("카테고리 이름 수정 테스트")
    @Test
    @Transactional
    void updateInterestCategoryNameTest() {
        List<InterestCategory> interestCategoryList = interestCategoryServiceImpl.getInterestCategoryListByName("게임");
        System.out.println("interestCategoryList.size() = " + interestCategoryList.size());
        for(InterestCategory interestCategory : interestCategoryList){
            interestCategoryServiceImpl.updateInterestCategory(interestCategory.getCategoryName(), "뉴게임");
        }
        List<Interest> foundInterestList = interestServiceImpl.getInterestListByCategoryName("뉴게임");
        Assertions.assertThat(foundInterestList.size()).isEqualTo(4);
    }
}
