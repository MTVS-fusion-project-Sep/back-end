package com.mtvs.backend.furniture.wallfurniture.repository;

import com.mtvs.backend.furniture.wallfurniture.domain.WallFurniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WallFurnitureRepository extends JpaRepository<WallFurniture, Long> {

    @Query("SELECT wf FROM WallFurniture wf WHERE wf.furniName = :wallFurnitureName")
    WallFurniture findByWallFurnitureName(@Param("wallFurnitureName") String wallFurnitureName);

    @Query("SELECT wf FROM WallFurniture wf WHERE wf.furniOnPlace = :furniOnPlace")
    List<WallFurniture> findByWallFurnitureOnPlace(@Param("furniOnPlace") Boolean furniOnPlace);

    @Query("SELECT wf FROM WallFurniture wf WHERE wf.userId = :userId And wf.furniOnPlace = :furniOnPlace")
    List<WallFurniture> findByUserIdAndWallFurnitureOnPlace(@Param("userId") String userId, @Param("furniOnPlace") boolean furniOnPlace);

    @Query("SELECT wf FROM WallFurniture wf WHERE wf.furniCategory = :furniCategory")
    List<WallFurniture> findByWallFurnitureCategory(@Param("furniCategory") String furniCategory);

    @Query("SELECT wf FROM WallFurniture wf WHERE wf.userId = :userId")
    List<WallFurniture> findByWallFurnitureListByUserId(@Param("userId") String userId);

    @Modifying
    @Query("DELETE FROM WallFurniture wf WHERE wf.furniName = :furnitureName")
    void deleteByWallFurnitureName(@Param("furnitureName") String furnitureName);
}