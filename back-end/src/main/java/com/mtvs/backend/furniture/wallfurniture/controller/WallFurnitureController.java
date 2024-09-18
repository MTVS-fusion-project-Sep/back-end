package com.mtvs.backend.furniture.wallfurniture.controller;

import com.mtvs.backend.furniture.wallfurniture.domain.WallFurniture;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureRegisterDTO;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureUpdateDTO;
import com.mtvs.backend.furniture.wallfurniture.service.WallFurnitureServiceImpl;
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

    @GetMapping("/furnitureId/{wallFurnitureId}")
    ResponseEntity<WallFurniture> getWallFurniture(@PathVariable Long wallFurnitureId) {
        WallFurniture foundWallFurniture = wallFurnitureServiceImpl.getWallFurnitureById(wallFurnitureId);
        return ResponseEntity.ok(foundWallFurniture);
    }

    @GetMapping("/furnitureName/{wallFurnitureName}")
    ResponseEntity<WallFurniture> getWallFurnitureByName(@PathVariable String wallFurnitureName) {
        WallFurniture foundWallFurniture = wallFurnitureServiceImpl.getWallFurnitureByName(wallFurnitureName);
        return ResponseEntity.ok(foundWallFurniture);
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<List<WallFurniture>> getWallFurnitureListByUserId(@PathVariable String userId) {
        List<WallFurniture> foundWallFurnitureList = wallFurnitureServiceImpl.getWallFurnitureListByUserId(userId);
        return ResponseEntity.ok(foundWallFurnitureList);
    }

    @GetMapping("/furnitureCategory/{wallFurnitureCategory}")
    ResponseEntity<List<WallFurniture>> getWallFurnitureListByCategory(@PathVariable String wallFurnitureCategory) {
        return ResponseEntity.ok(wallFurnitureServiceImpl.getWallFurnitureListByCategory(wallFurnitureCategory));
    }

    @GetMapping("/list")
    ResponseEntity<List<WallFurniture>> getWallFurnitureList() {
        return ResponseEntity.ok(wallFurnitureServiceImpl.getAllWallFurniture());
    }

    @PostMapping
    ResponseEntity<WallFurniture> registerWallFurniture(@RequestBody WallFurnitureRegisterDTO wfrDTO) {
        return ResponseEntity.ok(wallFurnitureServiceImpl.registerWallFurniture(wfrDTO));
    }

    @PatchMapping
    ResponseEntity<WallFurniture> updateWallFurniture(@RequestBody WallFurnitureUpdateDTO wfrDTO) {
        return ResponseEntity.ok(wallFurnitureServiceImpl.updateWallFurniture(wfrDTO));
    }

    @DeleteMapping("/furnitureId/{wallFurnitureId}")
    ResponseEntity<?> deleteWallFurniture(@PathVariable Long wallFurnitureId) {
        wallFurnitureServiceImpl.deleteWallFurnitureById(wallFurnitureId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/furnitureName/{wallFurnitureName}")
    ResponseEntity<?> deleteWallFurnitureByName(@PathVariable String wallFurnitureName) {
        wallFurnitureServiceImpl.deleteWallFurnitureByName(wallFurnitureName);
        return ResponseEntity.noContent().build();
    }
}
