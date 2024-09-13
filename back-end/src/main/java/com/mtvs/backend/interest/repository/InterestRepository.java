package com.mtvs.backend.interest.repository;

import com.mtvs.backend.interest.domain.Interest;
import com.mtvs.backend.interest.domain.InterestCategory;
import com.mtvs.backend.interest.dto.InterestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findByCategory(InterestCategory interestCategory);

    @Query("SELECT i FROM Interest i WHERE i.category.categoryName = :categoryName")
    List<Interest> findByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT i FROM Interest i WHERE i.category.categoryName = :categoryName AND i.name = :interestName")
    Interest findByInterestCategoryNameAndInterestName(@Param("categoryName") String categoryName, @Param("interestName") String interestName);
}
