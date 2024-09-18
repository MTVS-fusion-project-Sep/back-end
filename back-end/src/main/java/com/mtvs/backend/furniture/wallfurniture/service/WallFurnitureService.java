package com.mtvs.backend.furniture.wallfurniture.service;

import com.mtvs.backend.furniture.wallfurniture.domain.WallFurniture;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureRegisterDTO;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureUpdateDTO;

import java.util.List;

public interface WallFurnitureService {

    WallFurniture registerWallFurniture(WallFurnitureRegisterDTO wallFurnitureRegisterDTO);
    List<WallFurniture> getAllWallFurniture();
    WallFurniture getWallFurnitureById(long id);
    WallFurniture getWallFurnitureByName(String name);
    List<WallFurniture> getWallFurnitureListByOnPlace(boolean onPlace);
    List<WallFurniture> getWallFurnitureListByUserIdAndOnPlace(String userId, boolean onPlace);
    List<WallFurniture> getWallFurnitureListByCategory(String category);
    List<WallFurniture> getWallFurnitureListByUserId(String userId);
    WallFurniture updateWallFurniture(WallFurnitureUpdateDTO wfuDTO);
    void deleteWallFurnitureById(long id);
    void deleteWallFurnitureByName(String name);
}
