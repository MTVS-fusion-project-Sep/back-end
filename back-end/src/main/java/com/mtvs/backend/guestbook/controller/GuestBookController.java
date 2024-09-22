package com.mtvs.backend.guestbook.controller;

import com.mtvs.backend.guestbook.domain.GuestBook;
import com.mtvs.backend.guestbook.dto.GuestBookRegisterDTO;
import com.mtvs.backend.guestbook.dto.GuestBookUpdateDTO;
import com.mtvs.backend.guestbook.service.GuestBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("guest-book")
public class GuestBookController {

    private final GuestBookServiceImpl guestBookServiceImpl;

    @Autowired
    public GuestBookController(GuestBookServiceImpl guestBookServiceImpl) {
        this.guestBookServiceImpl = guestBookServiceImpl;
    }

    @PostMapping
    ResponseEntity<GuestBook> registGuestBook(@RequestBody GuestBookRegisterDTO gbrDTO) {
        GuestBook newGuestBook = new GuestBook(gbrDTO.getToUserId(), gbrDTO.getToUserId(), gbrDTO.getContent(), gbrDTO.getRegistDate());
        return ResponseEntity.ok(guestBookServiceImpl.registerGuestBook(newGuestBook));
    }

    @GetMapping
    ResponseEntity<GuestBook> findGuestBookById(@RequestParam("guestBookId") Long id) {
        return ResponseEntity.ok(guestBookServiceImpl.findGuestBookById(id));
    }


    @GetMapping("/writer")
    ResponseEntity<List<GuestBook>> findGuestBookByWriterId(@RequestParam("writerId") String writerId) {
        return ResponseEntity.ok(guestBookServiceImpl.findGuestBooksByWriterId(writerId));
    }

    @GetMapping("/reader")
    ResponseEntity<List<GuestBook>> findGuestBookByReaderId(@RequestParam("readerId") String readerId) {
        return ResponseEntity.ok(guestBookServiceImpl.findGuestBookByReaderId(readerId));
    }

    @GetMapping("/list")
    ResponseEntity<List<GuestBook>> findAllGuestBooks() {
        return ResponseEntity.ok(guestBookServiceImpl.findAllGuestBooks());
    }

    @PatchMapping
    ResponseEntity<GuestBook> updateGuestBookById(@RequestBody GuestBookUpdateDTO gbuDTO) {
        GuestBook foundGuestBook = guestBookServiceImpl.findGuestBookById(gbuDTO.getId());
        foundGuestBook.setContent(gbuDTO.getContent());
        foundGuestBook.setToUserId(gbuDTO.getToUserId());
        foundGuestBook.setFromUserId(gbuDTO.getFromUserId());
        foundGuestBook.setRegistDate(gbuDTO.getRegistDate());
        return ResponseEntity.ok(guestBookServiceImpl.updateGuestBook(foundGuestBook));
    }

    @DeleteMapping
    ResponseEntity<GuestBook> deleteGuestBookById(@RequestParam("guestBookId") Long id) {
        guestBookServiceImpl.removeGuestBook(id);
        return ResponseEntity.noContent().build();
    }
}
