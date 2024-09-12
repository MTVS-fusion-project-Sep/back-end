package com.mtvs.backend.furniture.groundfurniture.domain;

import com.mtvs.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="furniture")
@Getter @Setter
public class GroundFurniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="furni_size_x")
    private int furniSizeX;

    @Column(name="furni_size_z")
    private int furniSizeZ;

    @Column(name="on_place")
    private boolean onPlace;

    @Column(name="furni_current_x")
    private int furniCurrentX;

    @Column(name="furni_current_z")
    private int furniCurrentZ;

    @Column(name="furni_rotate")
    private boolean furniRotate;

    @Column(name="furni_category")
    private String furniCategory;

    @Column(name="furni_name")
    private String furniName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
