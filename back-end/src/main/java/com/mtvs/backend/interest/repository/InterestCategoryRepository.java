package com.mtvs.backend.interest.repository;

import com.mtvs.backend.interest.domain.InterestCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterestCategoryRepository extends JpaRepository<InterestCategory, Long> {
    @Query("SELECT c FROM InterestCategory c WHERE c.categoryName = :categoryName")
   Optional<InterestCategory> findByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT ic FROM InterestCategory ic WHERE ic.categoryName = :categoryName")
    List<InterestCategory> findInterestCategoryListByCategoryName(@Param("categoryName") String categoryName);
}
