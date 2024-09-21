package com.mtvs.backend.furniture.groundfurniture.domain;

import com.mtvs.backend.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "땅가구", requiredProperties = {"id"})
@Entity
@Table(name="ground_furniture")
@Getter @Setter
public class GroundFurniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "땅가구 고유 아이디", example = "1")
    private Long id;

    @Column(name="furni_size_x")
    private int furniSizeX;

    @Column(name="furni_size_z")
    private int furniSizeZ;

    @Schema(description = "가구 설치 여부")
    @Column(name="furni_on_place")
    private boolean onPlace;

    @Column(name="furni_current_x")
    private int furniCurrentX;

    @Column(name="furni_current_z")
    private int furniCurrentZ;

    @Schema(description = "가구 회전 여부")
    @Column(name="furni_rotate")
    private boolean furniRotate;

    @Schema(description = "땅가구 카테고리 이름")
    @Column(name="furni_category")
    private String furniCategory;

    @Column(name="furni_name")
    private String furniName;

    @Column(name="user_id")
    private String userId;

    public GroundFurniture() {
    }

    public GroundFurniture(int furniSizeX, int furniSizeZ, boolean onPlace, int furniCurrentX, int furniCurrentZ, boolean furniRotate, String furniCategory, String furniName, String userId) {
        this.furniSizeX = furniSizeX;
        this.furniSizeZ = furniSizeZ;
        this.onPlace = onPlace;
        this.furniCurrentX = furniCurrentX;
        this.furniCurrentZ = furniCurrentZ;
        this.furniRotate = furniRotate;
        this.furniCategory = furniCategory;
        this.furniName = furniName;
        this.userId = userId;
    }
}
