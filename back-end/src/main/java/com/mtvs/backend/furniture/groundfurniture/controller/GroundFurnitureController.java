package com.mtvs.backend.furniture.groundfurniture.controller;

import com.mtvs.backend.furniture.groundfurniture.domain.GroundFurniture;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureRegisterDTO;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureUpdateDTO;
import com.mtvs.backend.furniture.groundfurniture.service.GroundFurnitureServiceImpl;
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

    @GetMapping("/{groundFurnitureId}")
    ResponseEntity<GroundFurniture> getGroundFurnitureById(@PathVariable("groundFurnitureId") long gfi) {
        GroundFurniture foundGroundFurniture = groundFurnitureServiceImpl.getGroundFurnitureById(gfi);
        return ResponseEntity.ok(foundGroundFurniture);
    }

    @GetMapping("/{groundFurnitureName}")
    ResponseEntity<GroundFurniture> getGroundFurnitureByName(@PathVariable("groundFurnitureName") String gfn) {
        GroundFurniture foundGroundFurniture = groundFurnitureServiceImpl.getGroundFurnitureByName(gfn);
        return ResponseEntity.ok(foundGroundFurniture);
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<GroundFurniture>> getGroundFurnitureListByUserId(@PathVariable("userId") String userId) {
        List<GroundFurniture> foundGroundFurnitureList = groundFurnitureServiceImpl.getGroundFurnitureListByUserId(userId);
        return ResponseEntity.ok(foundGroundFurnitureList);
    }

    @GetMapping("/{groundFurnitureCategory}")
    ResponseEntity<List<GroundFurniture>> getGroundFurnitureListByCategory(@PathVariable("groundFurnitureCategory") String category) {
        List<GroundFurniture> foundGroundFurnitureList = groundFurnitureServiceImpl.getGroundFurnitureListByCategory(category);
        return ResponseEntity.ok(foundGroundFurnitureList);
    }

    @GetMapping("/list")
    ResponseEntity<List<GroundFurniture>> getGroundFurnitureList() {
        List<GroundFurniture> foundGroundFurnitureList = groundFurnitureServiceImpl.getAllGroundFurniture();
        return ResponseEntity.ok(foundGroundFurnitureList);
    }

    @PostMapping
    ResponseEntity<GroundFurniture> registerGroundFurniture(@RequestBody GroundFurnitureRegisterDTO gfrDTO) {
        return ResponseEntity.ok(groundFurnitureServiceImpl.registerGroundFurniture(gfrDTO));
    }

    @PatchMapping
    ResponseEntity<GroundFurniture> updateGroundFurniture(@RequestBody GroundFurnitureUpdateDTO gfrDTO) {
        return ResponseEntity.ok(groundFurnitureServiceImpl.updateGroundFurniture(gfrDTO));
    }

    @DeleteMapping("/{groundFurnitureId}")
    ResponseEntity<?> deleteGroundFurnitureById(@PathVariable("groundFurnitureId") long gfi) {
       groundFurnitureServiceImpl.deleteGroundFurnitureById(gfi);
       return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{groundFurnitureName}")
    ResponseEntity<?> deleteGroundFurnitureByName(@PathVariable("groundFurnitureName") String gfn) {
        groundFurnitureServiceImpl.deleteGroundFurnitureByName(gfn);
        return ResponseEntity.noContent().build();
    }
}
