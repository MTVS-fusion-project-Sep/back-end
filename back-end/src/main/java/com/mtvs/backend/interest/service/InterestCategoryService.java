package com.mtvs.backend.interest.service;

import com.mtvs.backend.interest.domain.InterestCategory;

import java.util.List;

public interface InterestCategoryService {

    List<InterestCategory> getCategoryList();
    InterestCategory createInterestCategory(String name);
    void deleteInterestCategory(Long categoryId);
    InterestCategory getInterestCategoryByCategoryId(Long categoryId);
    InterestCategory getInterestCategoryByName(String categoryName);
    List<InterestCategory> getInterestCategoryListByName(String name);
    void updateInterestCategory(String interestCategoryName, String newName);
}
