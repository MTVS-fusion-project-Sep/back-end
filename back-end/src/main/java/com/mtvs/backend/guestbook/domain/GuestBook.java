package com.mtvs.backend.guestbook.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "guest_book")
@Getter
@Setter
public class GuestBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "to_user_id") // 방명록을 남기는 유저 아이디
    String toUserId;

    @Column(name = "from_user_id") // 방명록이 기록되는 유저 아이디
    String fromUserId;

    @Column(name = "content")
    String content;

    @Column(name = "regist_date")
    String registDate;

    public GuestBook() {
    }

    public GuestBook(String toUserId, String fromUserId, String content, String registDate) {
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.content = content;
        this.registDate = registDate;
    }
}
