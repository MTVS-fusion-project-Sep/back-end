package com.mtvs.backend.furniture.wallfurniture.dto;

import com.mtvs.backend.furniture.wallfurniture.domain.WallFurniPosType;
import com.mtvs.backend.user.domain.User;
import lombok.Getter;

@Getter
public class WallFurnitureUpdateDTO {

    private Long id;
    private String furniCategory;
    private String furniName;
    private WallFurniPosType furniPos;
    private boolean furniOnPlace;

    public WallFurnitureUpdateDTO() {
    }

    public WallFurnitureUpdateDTO(Long id, String furniCategory, String furniName, WallFurniPosType furniPos, boolean furniOnPlace) {
        this.id = id;
        this.furniCategory = furniCategory;
        this.furniName = furniName;
        this.furniPos = furniPos;
        this.furniOnPlace = furniOnPlace;
    }

}