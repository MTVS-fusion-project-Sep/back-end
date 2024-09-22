package com.mtvs.backend.guestbook;

import com.mtvs.backend.guestbook.domain.GuestBook;
import com.mtvs.backend.guestbook.repository.GuestBookRepository;
import com.mtvs.backend.guestbook.service.GuestBookServiceImpl;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.dto.UserRegisterDTO;
import com.mtvs.backend.user.repository.UserRepository;
import com.mtvs.backend.user.service.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GuestBookTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GuestBookRepository guestBookRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private GuestBookServiceImpl guestBookServiceImpl;

    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();
        guestBookRepository.deleteAll();

        entityManager.createNativeQuery("ALTER TABLE user AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE guest_book AUTO_INCREMENT = 1").executeUpdate();
        entityManager.flush();

        userServiceImpl.registerUser(new UserRegisterDTO("user1", "1234", "user1", "2001-11-23", "man"));
        userServiceImpl.registerUser(new UserRegisterDTO("user2", "1234", "user2", "2001-11-23", "woman"));
        userServiceImpl.registerUser(new UserRegisterDTO("user3", "1234", "user3", "2001-11-23", "man"));

        guestBookServiceImpl.registerGuestBook(new GuestBook("user1", "user2", "guestbook1", "2024-09-18"));
        guestBookServiceImpl.registerGuestBook(new GuestBook("user1", "user3", "guestbook2", "2024-09-19"));
        guestBookServiceImpl.registerGuestBook(new GuestBook("user2", "user3", "guestbook3", "2024-09-20"));
        guestBookServiceImpl.registerGuestBook(new GuestBook("user2", "user1", "guestbook4", "2024-09-21"));
    }

    @DisplayName("방명록 등록 테스트")
    @Test
    @Transactional
    void registerGuestBookTest() {
        Assertions.assertThat(guestBookRepository.count()).isEqualTo(4);
    }
    
    @DisplayName("방명록 writerId로 조회 테스트")
    @Test
    @Transactional
    void findGuestBookByWriterId() {
        List<GuestBook> foundGuestBookList = guestBookServiceImpl.findGuestBooksByWriterId("user1");
        Assertions.assertThat(foundGuestBookList.size()).isEqualTo(2);
    }

    @DisplayName("방명록 readerId로 조회 테스트")
    @Test
    @Transactional
    void findGuestBookByReaderId() {
        List<GuestBook> foundGuestBookList = guestBookServiceImpl.findGuestBookByReaderId("user2");
        Assertions.assertThat(foundGuestBookList.size()).isEqualTo(1);
        Assertions.assertThat(foundGuestBookList.get(0).getContent()).isEqualTo("guestbook1");
    }

    @DisplayName("방명록 수정 테스트")
    @Test
    @Transactional
    void updateGuestBookTest() {
        List<GuestBook> foundGuestBookList = guestBookServiceImpl.findGuestBooksByWriterId("user1");
        GuestBook foundGuestBook = guestBookServiceImpl.findGuestBookById(foundGuestBookList.get(0).getId());
        GuestBook newGuestBook = new GuestBook();
        newGuestBook.setId(foundGuestBook.getId());
        newGuestBook.setContent("newGuestBook1");
        newGuestBook.setToUserId(foundGuestBook.getToUserId());
        newGuestBook.setFromUserId(foundGuestBook.getFromUserId());
        newGuestBook.setRegistDate(foundGuestBook.getRegistDate());
        guestBookServiceImpl.updateGuestBook(newGuestBook);
        Assertions.assertThat(guestBookServiceImpl.findGuestBookById(foundGuestBook.getId()).getContent()).isEqualTo("newGuestBook1");
    }
    
    @DisplayName("방명록 삭제 테스트")
    @Test
    @Transactional
    void deleteGuestBookTest() {
        List<GuestBook> foundGuestBookList = guestBookServiceImpl.findGuestBooksByWriterId("user1");
        List<GuestBook> allGuestBook = guestBookServiceImpl.findAllGuestBooks();
        guestBookServiceImpl.removeGuestBook(foundGuestBookList.get(0).getId());
        Assertions.assertThat(guestBookRepository.count()).isEqualTo(allGuestBook.size() - 1);
        Assertions.assertThat(guestBookServiceImpl.findGuestBooksByWriterId("user1").size()).isEqualTo(foundGuestBookList.size() - 1);
    }
}
