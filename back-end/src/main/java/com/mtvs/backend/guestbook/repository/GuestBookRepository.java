package com.mtvs.backend.guestbook.repository;

import com.mtvs.backend.guestbook.domain.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    @Query("SELECT gb FROM GuestBook gb WHERE gb.toUserId = :writerId")
    List<GuestBook> findGuestBookByWriterId(@Param("writerId") String writerId);

    @Query("SELECT gb FROM GuestBook gb WHERE gb.fromUserId = :readerId")
    List<GuestBook> findGuestBookByReaderId(@Param("readerId") String readerId);
}
