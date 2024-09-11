package com.mtvs.backend.interest.controller;

import com.mtvs.backend.interest.domain.InterestCategory;
import com.mtvs.backend.interest.dto.InterestCategoryUpdateDTO;
import com.mtvs.backend.interest.service.InterestCategoryService;
import com.mtvs.backend.interest.service.InterestCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interest-category")
public class InterestCategoryController {

    @Autowired
    private InterestCategoryService interestCategoryService;
    @Autowired
    private InterestCategoryServiceImpl interestCategoryServiceImpl;

    @GetMapping("/list")
    public ResponseEntity<List<InterestCategory>> getCategoryList() {
        List<InterestCategory> categories = interestCategoryService.getCategoryList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<InterestCategory> createCategory(@RequestBody String name) {
        InterestCategory category = interestCategoryService.createInterestCategory(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PatchMapping
    public ResponseEntity<InterestCategoryUpdateDTO> updateCategory(@RequestBody InterestCategoryUpdateDTO interestCategoryUpdateDTO){
        interestCategoryServiceImpl.updateInterestCategory(interestCategoryUpdateDTO.getInterestCategoryName(), interestCategoryUpdateDTO.getNewName());
        return ResponseEntity.status(HttpStatus.OK).body(interestCategoryUpdateDTO);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        interestCategoryService.deleteInterestCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
