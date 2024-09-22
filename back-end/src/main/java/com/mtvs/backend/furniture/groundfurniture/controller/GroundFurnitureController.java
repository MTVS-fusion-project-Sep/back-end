package com.mtvs.backend.furniture.groundfurniture.controller;

import com.mtvs.backend.furniture.groundfurniture.domain.GroundFurniture;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureRegisterDTO;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureUpdateDTO;
import com.mtvs.backend.furniture.groundfurniture.service.GroundFurnitureServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ground-furniture")
public class GroundFurnitureController {

    private final GroundFurnitureServiceImpl groundFurnitureServiceImpl;

    @Autowired
    public GroundFurnitureController(GroundFurnitureServiceImpl groundFurnitureServiceImpl) {
        this.groundFurnitureServiceImpl = groundFurnitureServiceImpl;
    }

    @Operation(summary = "땅가구 정보 조회", description = "땅가구 아이디로 특정 땅가구의 정보를 조회합니다.")
    @GetMapping("/furnitureId")
    ResponseEntity<GroundFurniture> getGroundFurnitureById(
            @Parameter(description = "조회할 땅가구의 ID", example = "1")
            @RequestParam("groundFurnitureId") long gfi) {
        GroundFurniture foundGroundFurniture = groundFurnitureServiceImpl.getGroundFurnitureById(gfi);
        return ResponseEntity.ok(foundGroundFurniture);
    }

    @Operation(summary = "땅가구 이름으로 조회", description = "이름으로 특정 땅가구의 정보를 조회합니다.")
    @GetMapping("/furnitureName")
    ResponseEntity<GroundFurniture> getGroundFurnitureByName(
            @Parameter(description = "조회할 땅가구의 이름", example = "Sofa")
            @RequestParam("groundFurnitureName") String gfn) {
        GroundFurniture foundGroundFurniture = groundFurnitureServiceImpl.getGroundFurnitureByName(gfn);
        return ResponseEntity.ok(foundGroundFurniture);
    }

    @Operation(summary = "유저 ID로 땅가구 목록 조회", description = "유저 ID로 특정 사용자가 소유한 땅가구 목록을 조회합니다.")
    @GetMapping("/user")
    ResponseEntity<List<GroundFurniture>> getGroundFurnitureListByUserId(
            @Parameter(description = "조회할 유저의 ID", example = "user1")
            @RequestParam("userId") String userId) {
        List<GroundFurniture> foundGroundFurnitureList = groundFurnitureServiceImpl.getGroundFurnitureListByUserId(userId);
        return ResponseEntity.ok(foundGroundFurnitureList);
    }

    @Operation(summary = "카테고리로 땅가구 조회", description = "카테고리로 특정 땅가구 목록을 조회합니다.")
    @GetMapping("/furnitureCategory")
    ResponseEntity<List<GroundFurniture>> getGroundFurnitureListByCategory(
            @Parameter(description = "조회할 가구 카테고리", example = "Living Room")
            @RequestParam("groundFurnitureCategory") String category) {
        List<GroundFurniture> foundGroundFurnitureList = groundFurnitureServiceImpl.getGroundFurnitureListByCategory(category);
        return ResponseEntity.ok(foundGroundFurnitureList);
    }

    @Operation(summary = "전체 땅가구 조회", description = "전체 땅가구 목록을 조회합니다.")
    @GetMapping("/list")
    ResponseEntity<List<GroundFurniture>> getGroundFurnitureList() {
        List<GroundFurniture> foundGroundFurnitureList = groundFurnitureServiceImpl.getAllGroundFurniture();
        return ResponseEntity.ok(foundGroundFurnitureList);
    }

    @Operation(summary = "땅가구 등록", description = "새로운 땅가구를 등록합니다.")
    @PostMapping
    ResponseEntity<GroundFurniture> registerGroundFurniture(
            @RequestBody GroundFurnitureRegisterDTO gfrDTO) {
        return ResponseEntity.ok(groundFurnitureServiceImpl.registerGroundFurniture(gfrDTO));
    }

    @Operation(summary = "땅가구 수정", description = "기존의 땅가구 정보를 수정합니다.")
    @PatchMapping
    ResponseEntity<GroundFurniture> updateGroundFurniture(
            @RequestBody GroundFurnitureUpdateDTO gfrDTO) {
        return ResponseEntity.ok(groundFurnitureServiceImpl.updateGroundFurniture(gfrDTO));
    }

    @Operation(summary = "땅가구 삭제 (ID)", description = "땅가구 ID로 특정 가구를 삭제합니다.")
    @DeleteMapping("/furnitureId")
    ResponseEntity<?> deleteGroundFurnitureById(
            @Parameter(description = "삭제할 땅가구의 ID", example = "1")
            @RequestParam("groundFurnitureId") long gfi) {
        groundFurnitureServiceImpl.deleteGroundFurnitureById(gfi);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "땅가구 삭제 (이름)", description = "땅가구 이름으로 특정 가구를 삭제합니다.")
    @DeleteMapping("/furnitureName")
    ResponseEntity<?> deleteGroundFurnitureByName(
            @Parameter(description = "삭제할 땅가구의 이름", example = "Sofa")
            @RequestParam("groundFurnitureName") String gfn) {
        groundFurnitureServiceImpl.deleteGroundFurnitureByName(gfn);
        return ResponseEntity.noContent().build();
    }
}
