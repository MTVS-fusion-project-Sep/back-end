package com.mtvs.backend.interest.dto;

import lombok.Getter;

@Getter
public class InterestCategoryUpdateDTO {

    private final String interestCategoryName;
    private final String newName;

    public InterestCategoryUpdateDTO(String interestCategoryName, String newName) {
        this.interestCategoryName = interestCategoryName;
        this.newName = newName;
    }
}
