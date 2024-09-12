package com.mtvs.backend.furniture.groundfurniture.service;

import com.mtvs.backend.furniture.groundfurniture.domain.GroundFurniture;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureRegisterDTO;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureUpdateDTO;

import java.util.List;

public interface GroundFurnitureService {

    GroundFurniture registerGroundFurniture(GroundFurnitureRegisterDTO g);
    GroundFurniture getGroundFurnitureById(long id);
    GroundFurniture getGroundFurnitureByName(String name);
    List<GroundFurniture> getAllGroundFurniture();
    List<GroundFurniture> getGroundFurnitureListByUserId(String userId);
    List<GroundFurniture> getGroundFurnitureListByCategory(String category);
    void deleteGroundFurnitureById(long id);
    void deleteGroundFurnitureByName(String name);
    GroundFurniture updateGroundFurniture(GroundFurnitureUpdateDTO g);
}
