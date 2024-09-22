package com.mtvs.backend.furniture.wallfurniture.dto;

import com.mtvs.backend.furniture.wallfurniture.domain.WallFurniPosType;
import com.mtvs.backend.user.domain.User;
import lombok.Getter;

@Getter
public class WallFurnitureRegisterDTO {

    private String furniCategory;
    private String furniName;
    private WallFurniPosType furniPos;
    private boolean furniOnPlace;
    private String userId;

    public WallFurnitureRegisterDTO() {
    }

    public WallFurnitureRegisterDTO(String furniCategory, String furniName, WallFurniPosType furniPos, boolean furniOnPlace, String userId) {
        this.furniCategory = furniCategory;
        this.furniName = furniName;
        this.furniPos = furniPos;
        this.furniOnPlace = furniOnPlace;
        this.userId = userId;
    }
}