package com.mtvs.backend.furniture.wallfurniture.controller;

import com.mtvs.backend.furniture.wallfurniture.domain.WallFurniture;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureRegisterDTO;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureUpdateDTO;
import com.mtvs.backend.furniture.wallfurniture.service.WallFurnitureServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wall-furniture")
public class WallFurnitureController {

    private final WallFurnitureServiceImpl wallFurnitureServiceImpl;

    @Autowired
    public WallFurnitureController(WallFurnitureServiceImpl wallFurnitureServiceImpl) {
        this.wallFurnitureServiceImpl = wallFurnitureServiceImpl;
    }

    @Operation(summary = "벽가구 ID로 조회", description = "벽가구 ID로 특정 벽가구의 정보를 조회합니다.")
    @GetMapping("/furnitureId")
    ResponseEntity<WallFurniture> getWallFurniture(
            @Parameter(description = "조회할 벽가구의 ID", example = "1")
            @RequestParam Long wallFurnitureId) {
        WallFurniture foundWallFurniture = wallFurnitureServiceImpl.getWallFurnitureById(wallFurnitureId);
        return ResponseEntity.ok(foundWallFurniture);
    }

    @Operation(summary = "벽가구 이름으로 조회", description = "이름으로 특정 벽가구의 정보를 조회합니다.")
    @GetMapping("/furnitureName")
    ResponseEntity<WallFurniture> getWallFurnitureByName(
            @Parameter(description = "조회할 벽가구의 이름", example = "Bookshelf")
            @RequestParam String wallFurnitureName) {
        WallFurniture foundWallFurniture = wallFurnitureServiceImpl.getWallFurnitureByName(wallFurnitureName);
        return ResponseEntity.ok(foundWallFurniture);
    }

    @Operation(summary = "유저 ID로 벽가구 목록 조회", description = "유저 ID로 특정 사용자가 소유한 벽가구 목록을 조회합니다.")
    @GetMapping("/user")
    ResponseEntity<List<WallFurniture>> getWallFurnitureListByUserId(
            @Parameter(description = "조회할 유저의 ID", example = "user1")
            @RequestParam String userId) {
        List<WallFurniture> foundWallFurnitureList = wallFurnitureServiceImpl.getWallFurnitureListByUserId(userId);
        return ResponseEntity.ok(foundWallFurnitureList);
    }

    @Operation(summary = "카테고리로 벽가구 조회", description = "카테고리로 벽가구 목록을 조회합니다.")
    @GetMapping("/furnitureCategory")
    ResponseEntity<List<WallFurniture>> getWallFurnitureListByCategory(
            @Parameter(description = "조회할 벽가구 카테고리", example = "Living Room")
            @RequestParam String wallFurnitureCategory) {
        return ResponseEntity.ok(wallFurnitureServiceImpl.getWallFurnitureListByCategory(wallFurnitureCategory));
    }

    @Operation(summary = "전체 벽가구 조회", description = "전체 벽가구 목록을 조회합니다.")
    @GetMapping("/list")
    ResponseEntity<List<WallFurniture>> getWallFurnitureList() {
        return ResponseEntity.ok(wallFurnitureServiceImpl.getAllWallFurniture());
    }

    @Operation(summary = "벽가구 등록", description = "새로운 벽가구를 등록합니다.")
    @PostMapping
    ResponseEntity<WallFurniture> registerWallFurniture(@RequestBody WallFurnitureRegisterDTO wfrDTO) {
        return ResponseEntity.ok(wallFurnitureServiceImpl.registerWallFurniture(wfrDTO));
    }

    @Operation(summary = "벽가구 수정", description = "기존 벽가구 정보를 수정합니다.")
    @PatchMapping
    ResponseEntity<WallFurniture> updateWallFurniture(@RequestBody WallFurnitureUpdateDTO wfrDTO) {
        return ResponseEntity.ok(wallFurnitureServiceImpl.updateWallFurniture(wfrDTO));
    }

    @Operation(summary = "벽가구 삭제 (ID)", description = "벽가구 ID로 특정 가구를 삭제합니다.")
    @DeleteMapping("/furnitureId")
    ResponseEntity<?> deleteWallFurniture(
            @Parameter(description = "삭제할 벽가구의 ID", example = "1")
            @RequestParam Long wallFurnitureId) {
        wallFurnitureServiceImpl.deleteWallFurnitureById(wallFurnitureId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "벽가구 삭제 (이름)", description = "벽가구 이름으로 특정 가구를 삭제합니다.")
    @DeleteMapping("/furnitureName")
    ResponseEntity<?> deleteWallFurnitureByName(
            @Parameter(description = "삭제할 벽가구의 이름", example = "Bookshelf")
            @RequestParam String wallFurnitureName) {
        wallFurnitureServiceImpl.deleteWallFurnitureByName(wallFurnitureName);
        return ResponseEntity.noContent().build();
    }
}
