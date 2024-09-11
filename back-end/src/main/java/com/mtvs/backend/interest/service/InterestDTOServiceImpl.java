package com.mtvs.backend.interest.service;

import com.mtvs.backend.interest.domain.Interest;
import com.mtvs.backend.interest.domain.InterestCategory;
import com.mtvs.backend.interest.dto.InterestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestDTOServiceImpl implements InterestDTOService {

    private InterestServiceImpl interestServiceImpl;
    private InterestCategoryServiceImpl interestCategoryServiceImpl;

    @Autowired
    public InterestDTOServiceImpl(InterestServiceImpl interestServiceImpl, InterestCategoryServiceImpl interestCategoryServiceImpl) {
        this.interestServiceImpl = interestServiceImpl;
        this.interestCategoryServiceImpl = interestCategoryServiceImpl;
    }

    @Override
    public void createInterestDTO(InterestDTO interestDTO) {
        InterestCategory foundInterestCategory = interestCategoryServiceImpl.getInterestCategoryByName(interestDTO.getCategoryName());
        if(foundInterestCategory == null){
            interestCategoryServiceImpl.createInterestCategory(interestDTO.getCategoryName());
        }

        Interest foundInterest = interestServiceImpl.getInterestByInterestCategoryNameAndInterestName(interestDTO);
        if(foundInterest == null){
            interestServiceImpl.createInterest(
                    interestCategoryServiceImpl.getInterestCategoryByName(interestDTO.getCategoryName()).getId(),
                    interestDTO.getInterestName()
            );
        }
    }
}
