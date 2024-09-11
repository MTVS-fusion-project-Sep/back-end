package com.mtvs.backend.interest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class InterestCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    public InterestCategory() {
    }

    public InterestCategory(String categoryName) {
        this.categoryName = categoryName;
    }


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Interest> interestList = new ArrayList<>();
}
