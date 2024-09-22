package com.mtvs.backend.interestV2.dto;

import lombok.Getter;

@Getter
public class InterestV2RegisterDTO {

    private String userId;
    private String likeCount;
    private String bigCategory;
    private String smallCategory;
    private String bigCategory2;
    private String smallCategory2;
    private String bigCategory3;
    private String smallCategory3;

    public InterestV2RegisterDTO() {
    }

    public InterestV2RegisterDTO(String userId, String likeCount, String bigCategory, String smallCategory, String bigCategory2, String smallCategory2, String bigCategory3, String smallCategory3) {
        this.userId = userId;
        this.likeCount = likeCount;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.bigCategory2 = bigCategory2;
        this.smallCategory2 = smallCategory2;
        this.bigCategory3 = bigCategory3;
        this.smallCategory3 = smallCategory3;
    }
}
