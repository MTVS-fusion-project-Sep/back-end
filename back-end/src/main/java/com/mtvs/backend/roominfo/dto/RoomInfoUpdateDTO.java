package com.mtvs.backend.roominfo.dto;

import lombok.Getter;

@Getter
public class RoomInfoUpdateDTO {

    private long id;
    private String wallPaperName;
    private String tileName;

    public RoomInfoUpdateDTO(long id, String wallPaperName, String tileName) {
        this.id = id;
        this.wallPaperName = wallPaperName;
        this.tileName = tileName;
    }
}
