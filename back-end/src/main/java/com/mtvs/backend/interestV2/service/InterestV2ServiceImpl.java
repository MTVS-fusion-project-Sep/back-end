package com.mtvs.backend.interestV2.service;

import com.mtvs.backend.interestV2.domain.InterestV2;
import com.mtvs.backend.interestV2.dto.InterestV2RegisterDTO;
import com.mtvs.backend.interestV2.dto.InterestV2UpdateDTO;
import com.mtvs.backend.interestV2.repository.InterestV2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestV2ServiceImpl implements InterestV2Service {

    private final InterestV2Repository interestV2Repository;

    @Autowired
    public InterestV2ServiceImpl(InterestV2Repository interestV2Repository) {
        this.interestV2Repository = interestV2Repository;
    }

    @Override
    public InterestV2RegisterDTO registInterestV2(InterestV2RegisterDTO irDTO) {
        InterestV2 newInterestV2 = new InterestV2(
                irDTO.getUserId(),
                irDTO.getLikeCount(),
                irDTO.getBigCategory(),
                irDTO.getSmallCategory(),
                irDTO.getBigCategory2(),
                irDTO.getSmallCategory2(),
                irDTO.getBigCategory3(),
                irDTO.getSmallCategory3()
        );

        interestV2Repository.save(newInterestV2);

        return irDTO;
    }

    @Override
    public List<InterestV2> getInterestV2ByUserId(String userId) {
        return interestV2Repository.getInterestV2ListByUserId(userId);
    }

    @Override
    public InterestV2UpdateDTO updateInterestV2(InterestV2UpdateDTO iuDTO) {
        InterestV2 foundInterest = interestV2Repository.getInterestV2ById(iuDTO.getId());
        foundInterest.setUserId(iuDTO.getUserId());
        foundInterest.setLikeCount(iuDTO.getLikeCount());
        foundInterest.setBigCategory(iuDTO.getBigCategory());
        foundInterest.setSmallCategory(iuDTO.getSmallCategory());
        foundInterest.setBigCategory2(iuDTO.getBigCategory2());
        foundInterest.setSmallCategory2(iuDTO.getSmallCategory2());
        foundInterest.setBigCategory3(iuDTO.getBigCategory3());
        foundInterest.setSmallCategory3(iuDTO.getSmallCategory3());
        interestV2Repository.save(foundInterest);
        return iuDTO;
    }

    @Override
    public void deleteInterestV2(Long id) {
        interestV2Repository.deleteById(id);
    }
}
