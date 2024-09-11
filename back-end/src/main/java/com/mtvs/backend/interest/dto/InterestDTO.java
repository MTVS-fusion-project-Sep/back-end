package com.mtvs.backend.interest.dto;

import lombok.Getter;

@Getter
public class InterestDTO {

    private final String categoryName;
    private final String interestName;

    public InterestDTO(String categoryName, String interestName) {
        this.categoryName = categoryName;
        this.interestName = interestName;
    }
}
