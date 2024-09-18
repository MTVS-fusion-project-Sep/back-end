package com.mtvs.backend.interest.controller;

import com.mtvs.backend.interest.domain.Interest;
import com.mtvs.backend.interest.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interest")
public class InterestController {

    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping
    public ResponseEntity<List<Interest>> getInterestList() {
        List<Interest> interests = interestService.getInterestList();
        return ResponseEntity.ok(interests);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Interest>> getInterestListByCategoryId(@PathVariable Long categoryId) {
        List<Interest> interests = interestService.getInterestListByCategory(categoryId);
        return ResponseEntity.ok(interests);
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<Interest> createInterest(@PathVariable Long categoryId, @RequestParam String name) {
        Interest interest = interestService.createInterest(categoryId, name);
        return ResponseEntity.status(HttpStatus.CREATED).body(interest);
    }

    @DeleteMapping("/{interestId}")
    public ResponseEntity<Void> deleteInterest(@PathVariable Long interestId) {
        interestService.deleteInterest(interestId);
        return ResponseEntity.noContent().build();
    }
}
