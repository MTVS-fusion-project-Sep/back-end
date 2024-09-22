package com.mtvs.backend.interestV2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="interest_v2")
@Getter @Setter
public class InterestV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private String userId;

    @Column(name="like_count")
    private String likeCount;

    @Column(name="big_category")
    private String bigCategory;

    @Column(name="small_category")
    private String smallCategory;

    @Column(name="big_category2")
    private String bigCategory2;

    @Column(name="small_category2")
    private String smallCategory2;

    @Column(name="big_category3")
    private String bigCategory3;

    @Column(name="small_category3")
    private String smallCategory3;

    public InterestV2(String userId, String likeCount, String bigCategory, String smallCategory, String bigCategory2, String smallCategory2, String bigCategory3, String smallCategory3) {
        this.userId = userId;
        this.likeCount = likeCount;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.bigCategory2 = bigCategory2;
        this.smallCategory2 = smallCategory2;
        this.bigCategory3 = bigCategory3;
        this.smallCategory3 = smallCategory3;
    }

    public InterestV2() {
    }

    @Override
    public String toString() {
        return "InterestV2{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", likeCount='" + likeCount + '\'' +
                ", bigCategory='" + bigCategory + '\'' +
                ", smallCategory='" + smallCategory + '\'' +
                ", bigCategory2='" + bigCategory2 + '\'' +
                ", smallCategory2='" + smallCategory2 + '\'' +
                ", bigCategory3='" + bigCategory3 + '\'' +
                ", smallCategory3='" + smallCategory3 + '\'' +
                '}';
    }
}
