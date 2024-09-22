package com.mtvs.backend.interestV2.service;

import com.mtvs.backend.interestV2.domain.InterestV2;
import com.mtvs.backend.interestV2.dto.InterestV2RegisterDTO;
import com.mtvs.backend.interestV2.dto.InterestV2UpdateDTO;

import java.util.List;

public interface InterestV2Service {

    InterestV2RegisterDTO registInterestV2(InterestV2RegisterDTO irDTO);
    List<InterestV2> getInterestV2ByUserId(String userId);
    InterestV2UpdateDTO updateInterestV2(InterestV2UpdateDTO iuDTO);
    void deleteInterestV2(Long id);

}
