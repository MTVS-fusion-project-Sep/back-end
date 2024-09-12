package com.mtvs.backend.furniture.groundfurniture.repository;

import com.mtvs.backend.furniture.groundfurniture.domain.GroundFurniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroundFurnitureRepository extends JpaRepository<GroundFurniture, Long> {

    GroundFurniture findByFurniName(String furniName);
    void deleteByFurniName(String furniName);

    @Query("SELECT gf FROM GroundFurniture gf WHERE gf.user.userId = :userId")
    List<GroundFurniture> findByUserId(@Param("userId") String userId);

    @Query("SELECT gf FROM GroundFurniture gf WHERE gf.furniCategory = :category")
    List<GroundFurniture> findByCategory(@Param("category") String category);
}
