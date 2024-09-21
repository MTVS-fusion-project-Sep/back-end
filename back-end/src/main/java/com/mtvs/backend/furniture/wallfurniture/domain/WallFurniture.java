package com.mtvs.backend.furniture.wallfurniture.domain;

import com.mtvs.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wall_furniture")
@Getter @Setter
public class WallFurniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "furni_category")
    private String furniCategory;

    @Column(name = "furni_name")
    private String furniName;

    @Column(name = "furni_pos")
    private WallFurniPosType furniPos;

    @Column(name = "furni_on_place")
    private boolean furniOnPlace;

    private String userId;

    @Override
    public String toString() {
        return "WallFurniture{" +
                "id=" + id +
                ", furniCategory='" + furniCategory + '\'' +
                ", furniName='" + furniName + '\'' +
                ", furniPos=" + furniPos +
                ", furniOnPlace=" + furniOnPlace +
                ", userId='" + userId + '\'' +
                '}';
    }
}
