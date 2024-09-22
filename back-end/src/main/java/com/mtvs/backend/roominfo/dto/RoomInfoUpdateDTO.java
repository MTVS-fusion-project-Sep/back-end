package com.mtvs.backend.roominfo.dto;

import lombok.Getter;

@Getter
public class RoomInfoUpdateDTO {

    private long id;
    private int wallIndex;
    private int tileIndex;

    public RoomInfoUpdateDTO() {
    }

    public RoomInfoUpdateDTO(long id, int wallIndex, int tileIndex) {
        this.id = id;
        this.wallIndex = wallIndex;
        this.tileIndex = tileIndex;
    }
}
