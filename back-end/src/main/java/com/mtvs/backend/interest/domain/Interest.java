package com.mtvs.backend.interest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_name")
    private InterestCategory category;

    public Interest() {
    }

    public Interest(String name, InterestCategory category) {
        this.name = name;
        this.category = category;
    }
}
