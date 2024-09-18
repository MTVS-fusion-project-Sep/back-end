package com.mtvs.backend.guestbook.service;

import com.mtvs.backend.guestbook.domain.GuestBook;

import java.util.List;

public interface GuestBookService {

    GuestBook registerGuestBook(GuestBook guestBook);
    GuestBook updateGuestBook(GuestBook guestBook);
    void removeGuestBook(Long id);
    GuestBook findGuestBookById(long id);
    List<GuestBook> findAllGuestBooks();
    List<GuestBook> findGuestBooksByWriterId(String writerId);
    List<GuestBook> findGuestBookByReaderId(String readerId);
}
