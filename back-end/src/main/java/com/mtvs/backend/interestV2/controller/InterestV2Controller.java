package com.mtvs.backend.interestV2.controller;

import com.mtvs.backend.interestV2.domain.InterestV2;
import com.mtvs.backend.interestV2.dto.InterestV2RegisterDTO;
import com.mtvs.backend.interestV2.dto.InterestV2UpdateDTO;
import com.mtvs.backend.interestV2.service.InterestV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interestv2")
public class InterestV2Controller {

    private final InterestV2Service interestV2Service;

    @Autowired
    public InterestV2Controller(InterestV2Service interestV2Service) {
        this.interestV2Service = interestV2Service;
    }

    @PostMapping
    ResponseEntity<InterestV2RegisterDTO> registInterestV2(@RequestBody InterestV2RegisterDTO interestV2RegisterDTO) {
        return ResponseEntity.ok(interestV2Service.registInterestV2(interestV2RegisterDTO));
    }

    ResponseEntity<List<InterestV2>> getInterestV2ListByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(interestV2Service.getInterestV2ByUserId(userId));
    }

    ResponseEntity<InterestV2UpdateDTO> updateInterestV2(@RequestBody InterestV2UpdateDTO interestV2UpdateDTO) {
        return ResponseEntity.ok(interestV2Service.updateInterestV2(interestV2UpdateDTO));
    }

    ResponseEntity<?> deleteInterestV2(@RequestParam Long interestId) {
        interestV2Service.deleteInterestV2(interestId);
        return ResponseEntity.noContent().build();
    }
}
