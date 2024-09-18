package com.mtvs.backend.interest.service;

import com.mtvs.backend.interest.domain.Interest;
import com.mtvs.backend.interest.dto.InterestDTO;

import java.util.List;

public interface InterestService {

    Interest createInterest(Long categoryId, String interestName);
    List<Interest> getInterestList();
    List<Interest> getInterestListByCategory(Long categoryId);
    List<Interest> getInterestListByCategoryName(String categoryName);
    Interest getInterestByInterestCategoryNameAndInterestName(InterestDTO interestDTO);
    void deleteInterest(Long interestId);
    void updateInterestByCategoryName(String existingInterestCategoryName, String newInterestCategoryName, String interestName);
    void updateInterestByInterestName(String existingInterestCategoryName, String newInterestName, String interestName);
}
