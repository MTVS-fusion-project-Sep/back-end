package com.mtvs.backend.interestV2.repository;

import com.mtvs.backend.interestV2.domain.InterestV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestV2Repository extends JpaRepository<InterestV2, Long> {

    @Query("SELECT i FROM InterestV2 i WHERE i.userId = :userId")
    List<InterestV2> getInterestV2ListByUserId(@Param("userId") String userId);

    @Query("SELECT i FROM InterestV2 i WHERE i.id = :interestV2Id")
    InterestV2 getInterestV2ById(@Param("interestV2Id") Long interestV2Id);
}
