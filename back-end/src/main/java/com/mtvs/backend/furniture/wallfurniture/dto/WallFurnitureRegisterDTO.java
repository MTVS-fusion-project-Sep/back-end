package com.mtvs.backend.furniture.wallfurniture.dto;

import com.mtvs.backend.user.domain.User;
import lombok.Getter;

@Getter
public class WallFurnitureRegisterDTO {

    private String furniCategory;
    private String furniName;
    private int furniPos;
    private boolean furniOnPlace;
    private User user;

    public WallFurnitureRegisterDTO(String furniCategory, String furniName, int furniPos, boolean furniOnPlace, User user) {
        this.furniCategory = furniCategory;
        this.furniName = furniName;
        this.furniPos = furniPos;
        this.furniOnPlace = furniOnPlace;
        this.user = user;
    }
}
