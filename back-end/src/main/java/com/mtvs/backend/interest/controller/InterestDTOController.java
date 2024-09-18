package com.mtvs.backend.interest.controller;

import com.mtvs.backend.interest.dto.InterestDTO;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interest")
public class InterestDTOController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public InterestDTOController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PatchMapping
    public void updateUserInterestList(String userId, List<InterestDTO> interestDTOList) {
        userServiceImpl.updateInterestList(userId, interestDTOList);
    }

}
