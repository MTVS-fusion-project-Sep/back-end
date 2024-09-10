package com.mtvs.backend.user.domain;

import com.mtvs.backend.interest.domain.Interest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private String userId;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="nickname")
    private String userNickname;

    @Column(name="birthday")
    private String birthday;

    @Column(name="gender")
    private String gender;

    @ManyToMany
    @JoinTable(
            name = "user_interests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private List<Interest> interests = new ArrayList<>();


    public User() {}

    public User(String userId, String userPassword, String userNickname, String birthday, String gender) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.birthday = birthday;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
