package com.mtvs.backend.roominfo.dto;

import lombok.Getter;

@Getter
public class RoomInfoRegisterDTO {

    private String wallPaperName;
    private String tileName;
    private String userId;

    public RoomInfoRegisterDTO(String wallPaperName, String tileName, String userId) {
        this.wallPaperName = wallPaperName;
        this.tileName = tileName;
        this.userId = userId;
    }
}
