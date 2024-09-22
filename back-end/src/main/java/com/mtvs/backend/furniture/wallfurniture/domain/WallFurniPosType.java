package com.mtvs.backend.furniture.wallfurniture.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WallFurniPosType {
    NONE(0),
    LEFT_TWO(1),
    RIGHT_ONE(2),
    RIGHT_TWO(3);

    private final int value;

    WallFurniPosType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
