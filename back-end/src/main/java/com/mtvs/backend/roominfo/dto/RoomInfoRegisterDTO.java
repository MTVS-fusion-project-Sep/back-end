package com.mtvs.backend.roominfo.dto;

import lombok.Getter;

@Getter
public class RoomInfoRegisterDTO {

    private int wallIndex;
    private int tileIndex;
    private String userId;

    public RoomInfoRegisterDTO() {
    }

    public RoomInfoRegisterDTO(int wallIndex, int tileIndex, String userId) {
        this.wallIndex = wallIndex;
        this.tileIndex = tileIndex;
        this.userId = userId;
    }
}
