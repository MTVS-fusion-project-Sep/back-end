package com.mtvs.backend.user.dto;

import lombok.Getter;

@Getter
public class UserDTO {

    private String userId;
    private String userPassword;
    private String userNickname;
    private String birthday;
    private String gender;

    public UserDTO() {
    }

    public UserDTO(String userId, String userPassword, String userNickname, String birthday, String gender) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.birthday = birthday;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
