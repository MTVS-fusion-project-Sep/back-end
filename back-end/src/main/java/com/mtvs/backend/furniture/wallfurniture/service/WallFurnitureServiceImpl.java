package com.mtvs.backend.furniture.wallfurniture.service;

import com.mtvs.backend.furniture.wallfurniture.domain.WallFurniture;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureRegisterDTO;
import com.mtvs.backend.furniture.wallfurniture.dto.WallFurnitureUpdateDTO;
import com.mtvs.backend.furniture.wallfurniture.repository.WallFurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WallFurnitureServiceImpl implements WallFurnitureService{

    private final WallFurnitureRepository wallFurnitureRepository;

    @Autowired
    public WallFurnitureServiceImpl(WallFurnitureRepository wallFurnitureRepository) {
        this.wallFurnitureRepository = wallFurnitureRepository;
    }

    @Override
    public WallFurniture registerWallFurniture(WallFurnitureRegisterDTO wallFurnitureRegisterDTO) {
        WallFurniture wallFurniture = new WallFurniture();
        wallFurniture.setFurniCategory(wallFurnitureRegisterDTO.getFurniCategory());
        wallFurniture.setFurniName(wallFurnitureRegisterDTO.getFurniName());
        wallFurniture.setFurniPos(wallFurnitureRegisterDTO.getFurniPos());
        wallFurniture.setFurniOnPlace(wallFurnitureRegisterDTO.isFurniOnPlace());
        wallFurniture.setUserId(wallFurnitureRegisterDTO.getUserId());
        return wallFurnitureRepository.save(wallFurniture);
    }

    @Override
    public List<WallFurniture> getAllWallFurniture() {
        return wallFurnitureRepository.findAll();
    }

    @Override
    public WallFurniture getWallFurnitureById(long id) {
        return wallFurnitureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found furniture id: " + id));
    }

    @Override
    public WallFurniture getWallFurnitureByName(String name) {
        return wallFurnitureRepository.findByWallFurnitureName(name);
    }

    @Override
    public List<WallFurniture> getWallFurnitureListByOnPlace(boolean onPlace) {
        return wallFurnitureRepository.findByWallFurnitureOnPlace(onPlace);
    }

    @Override
    public List<WallFurniture> getWallFurnitureListByUserIdAndOnPlace(String userId, boolean onPlace) {
        return wallFurnitureRepository.findByUserIdAndWallFurnitureOnPlace(userId, onPlace);
    }

    @Override
    public List<WallFurniture> getWallFurnitureListByCategory(String category) {
        return wallFurnitureRepository.findByWallFurnitureCategory(category);
    }

    @Override
    public List<WallFurniture> getWallFurnitureListByUserId(String userId) {
        return wallFurnitureRepository.findByWallFurnitureListByUserId(userId);
    }

    @Override
    public WallFurniture updateWallFurniture(WallFurnitureUpdateDTO wfuDTO) {
        WallFurniture foundWF = wallFurnitureRepository.findById(wfuDTO.getId()).orElseThrow(() -> new IllegalArgumentException("not found furniture id: " + wfuDTO.getId()));
        foundWF.setId(wfuDTO.getId());
        foundWF.setFurniCategory(wfuDTO.getFurniCategory());
        foundWF.setFurniName(wfuDTO.getFurniName());
        foundWF.setFurniPos(wfuDTO.getFurniPos());
        foundWF.setFurniOnPlace(wfuDTO.isFurniOnPlace());
        return wallFurnitureRepository.save(foundWF);
    }

    @Override
    public void deleteWallFurnitureById(long id) {
        wallFurnitureRepository.deleteById(id);
    }

    @Override
    public void deleteWallFurnitureByName(String name) {
        wallFurnitureRepository.deleteByWallFurnitureName(name);
    }
}