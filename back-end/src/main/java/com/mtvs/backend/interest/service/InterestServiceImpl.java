package com.mtvs.backend.interest.service;

import com.mtvs.backend.interest.domain.Interest;
import com.mtvs.backend.interest.domain.InterestCategory;
import com.mtvs.backend.interest.dto.InterestDTO;
import com.mtvs.backend.interest.repository.InterestCategoryRepository;
import com.mtvs.backend.interest.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestServiceImpl implements InterestService{

    private final InterestRepository interestRepository;
    private final InterestCategoryRepository interestCategoryRepository;
    private final InterestCategoryServiceImpl interestCategoryServiceImpl;

    @Autowired
    public InterestServiceImpl(InterestRepository interestRepository, InterestCategoryRepository interestCategoryRepository, InterestCategoryServiceImpl interestCategoryServiceImpl) {
        this.interestRepository = interestRepository;
        this.interestCategoryRepository = interestCategoryRepository;
        this.interestCategoryServiceImpl = interestCategoryServiceImpl;
    }

    @Override
    public Interest createInterest(Long categoryId, String interestName) {
        InterestCategory category = interestCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        Interest interest = new Interest();
        interest.setName(interestName);
        interest.setCategory(category);
        interestRepository.save(interest);

        return interest;
    }

    @Override
    public List<Interest> getInterestList() {
        return interestRepository.findAll();
    }

    @Override
    public List<Interest> getInterestListByCategory(Long categoryId) {
        InterestCategory category = interestCategoryServiceImpl.getInterestCategoryByCategoryId(categoryId);
        return interestRepository.findByCategory(category);
    }

    @Override
    public List<Interest> getInterestListByCategoryName(String categoryName) {
        InterestCategory category = interestCategoryServiceImpl.getInterestCategoryByName(categoryName);
        return interestRepository.findByCategory(category);
    }

    @Override
    public Interest getInterestByInterestCategoryNameAndInterestName(InterestDTO interestDTO) {
        return interestRepository.findByInterestCategoryNameAndInterestName(interestDTO.getCategoryName(), interestDTO.getInterestName());
    }

    @Override
    public void deleteInterest(Long interestId) {
        Interest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new IllegalArgumentException("관심사를 찾을 수 없습니다."));
        interestRepository.delete(interest);
    }

    @Override
    public void updateInterestByCategoryName(String existingInterestCategoryName, String newInterestCategoryName, String interestName) {
        Interest foundInterest = interestRepository.findByInterestCategoryNameAndInterestName(existingInterestCategoryName, newInterestCategoryName);
        foundInterest.getCategory().setCategoryName(interestName);
        interestRepository.save(foundInterest);
    }

    @Override
    public void updateInterestByInterestName(String existingInterestCategoryName, String newInterestName, String interestName) {
        Interest foundInterest = interestRepository.findByInterestCategoryNameAndInterestName(existingInterestCategoryName, interestName);
        foundInterest.setName(newInterestName);
        interestRepository.save(foundInterest);
    }
}
