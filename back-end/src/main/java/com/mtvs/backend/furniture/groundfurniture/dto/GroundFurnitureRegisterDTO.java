package com.mtvs.backend.furniture.groundfurniture.dto;

import lombok.Getter;

@Getter
public class GroundFurnitureRegisterDTO {

    private int furniSizeX;
    private int furniSizeZ;
    private boolean onPlace;
    private int furniCurrentX;
    private int furniCurrentZ;
    private boolean furniRotate;
    private String furniCategory;
    private String furniName;
    private String userId;

    public GroundFurnitureRegisterDTO(int furniSizeX, int furniSizeZ, boolean onPlace, int furniCurrentX, int furniCurrentZ, boolean furniRotate, String furniCategory, String furniName, String userId) {
        this.furniSizeX = furniSizeX;
        this.furniSizeZ = furniSizeZ;
        this.onPlace = onPlace;
        this.furniCurrentX = furniCurrentX;
        this.furniCurrentZ = furniCurrentZ;
        this.furniRotate = furniRotate;
        this.furniCategory = furniCategory;
        this.furniName = furniName;
        this.userId = userId;
    }
}
