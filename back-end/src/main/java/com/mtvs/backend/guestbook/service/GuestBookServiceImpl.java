package com.mtvs.backend.guestbook.service;

import com.mtvs.backend.guestbook.domain.GuestBook;
import com.mtvs.backend.guestbook.repository.GuestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookServiceImpl implements GuestBookService{

    private final GuestBookRepository guestBookRepository;

    @Autowired
    public GuestBookServiceImpl(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    @Override
    public GuestBook registerGuestBook(GuestBook guestBook) {
        return guestBookRepository.save(guestBook);
    }

    @Override
    public GuestBook updateGuestBook(GuestBook guestBook) {
        GuestBook foundGuestBook = guestBookRepository.findById(
                guestBook.getId()).orElseThrow(() -> new IllegalArgumentException("not found guestBook id: " + guestBook.getId())
        );
        foundGuestBook.setContent(guestBook.getContent());
        foundGuestBook.setFromUserId(guestBook.getFromUserId());
        foundGuestBook.setToUserId(guestBook.getToUserId());
        foundGuestBook.setRegistDate(guestBook.getRegistDate());
        return guestBookRepository.save(foundGuestBook);
    }

    @Override
    public void removeGuestBook(Long id) {
        guestBookRepository.deleteById(id);
    }

    @Override
    public GuestBook findGuestBookById(long id) {
        return guestBookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found guestBook id: " + id));
    }

    @Override
    public List<GuestBook> findAllGuestBooks() {
        return guestBookRepository.findAll();
    }

    @Override
    public List<GuestBook> findGuestBooksByWriterId(String writerId) {
        return guestBookRepository.findGuestBookByWriterId(writerId);
    }

    @Override
    public List<GuestBook> findGuestBookByReaderId(String readerId) {
        return guestBookRepository.findGuestBookByReaderId(readerId);
    }
}
