package com.mtvs.backend.user.dto;
import lombok.Getter;

@Getter
public class UserUpdateDTO {
    private Long id;
    private String userId;
    private String userPassword;
    private String userNickname;
    private String birthday;
    private String gender;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(Long id, String userId, String userPassword, String userNickname, String birthday, String gender) {
        this.id = id;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.birthday = birthday;
        this.gender = gender;
    }
}
