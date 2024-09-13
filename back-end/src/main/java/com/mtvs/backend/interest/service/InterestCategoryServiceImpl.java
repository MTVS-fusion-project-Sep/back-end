package com.mtvs.backend.interest.service;

import com.mtvs.backend.interest.domain.InterestCategory;
import com.mtvs.backend.interest.repository.InterestCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestCategoryServiceImpl implements InterestCategoryService{

    private final InterestCategoryRepository interestCategoryRepository;

    @Autowired
    public InterestCategoryServiceImpl(InterestCategoryRepository interestCategoryRepository) {
        this.interestCategoryRepository = interestCategoryRepository;
    }

    // 모든 카테고리 얻어옴
    @Override
    public List<InterestCategory> getCategoryList() {
        return interestCategoryRepository.findAll();
    }

    // 관심사 큰 카테고리 생성
    @Override
    public InterestCategory createInterestCategory(String name) {
        InterestCategory category = new InterestCategory(name);
        return interestCategoryRepository.save(category);
    }

    @Override
    public InterestCategory getInterestCategoryByCategoryId(Long categoryId) {
        return interestCategoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public InterestCategory getInterestCategoryByName(String categoryName) {
        return interestCategoryRepository.findByCategoryName(categoryName).orElse(null);
    }

    @Override
    public List<InterestCategory> getInterestCategoryListByName(String name) {
        return interestCategoryRepository.findInterestCategoryListByCategoryName(name);
    }

    // 큰 카테고리 삭제
    @Override
    public void deleteInterestCategory(Long categoryId) {
        InterestCategory category = interestCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        interestCategoryRepository.delete(category);
    }

    // 큰 카테고리 이름 수정
    @Override
    public void updateInterestCategory(String interestCategoryName, String newName) {
        InterestCategory interestCategory = interestCategoryRepository.findByCategoryName(interestCategoryName)
                        .orElse(null);
        if(interestCategory != null) {
            interestCategory.setCategoryName(newName);
            interestCategoryRepository.save(interestCategory);
        }
    }
}
