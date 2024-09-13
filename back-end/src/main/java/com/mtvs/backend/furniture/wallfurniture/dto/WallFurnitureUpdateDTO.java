package com.mtvs.backend.furniture.wallfurniture.dto;

import com.mtvs.backend.user.domain.User;
import lombok.Getter;

@Getter
public class WallFurnitureUpdateDTO {

    private Long id;
    private String furniCategory;
    private String furniName;
    private int furniPos;
    private boolean furniOnPlace;
    private User user;

    public WallFurnitureUpdateDTO(Long id, String furniCategory, String furniName, int furniPos, boolean furniOnPlace) {
        this.id = id;
        this.furniCategory = furniCategory;
        this.furniName = furniName;
        this.furniPos = furniPos;
        this.furniOnPlace = furniOnPlace;
    }

    public WallFurnitureUpdateDTO(Long id, String furniCategory, String furniName, int furniPos, boolean furniOnPlace, User user) {
        this.id = id;
        this.furniCategory = furniCategory;
        this.furniName = furniName;
        this.furniPos = furniPos;
        this.furniOnPlace = furniOnPlace;
        this.user = user;
    }
}
