package com.mtvs.backend.furniture.groundfurniture.service;

import com.mtvs.backend.furniture.groundfurniture.domain.GroundFurniture;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureRegisterDTO;
import com.mtvs.backend.furniture.groundfurniture.dto.GroundFurnitureUpdateDTO;
import com.mtvs.backend.furniture.groundfurniture.repository.GroundFurnitureRepository;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.service.UserServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroundFurnitureServiceImpl implements GroundFurnitureService {

    private final GroundFurnitureRepository repository;
    private final UserServiceImpl userServiceImpl;

    public GroundFurnitureServiceImpl(GroundFurnitureRepository repository, UserServiceImpl userServiceImpl) {
        this.repository = repository;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public GroundFurniture registerGroundFurniture(GroundFurnitureRegisterDTO gfrDTO) {
        GroundFurniture gf = new GroundFurniture();
        gf.setFurniSizeX(gfrDTO.getFurniSizeX());
        gf.setFurniSizeZ(gfrDTO.getFurniSizeZ());
        gf.setOnPlace(gfrDTO.isOnPlace());
        gf.setFurniCurrentX(gfrDTO.getFurniCurrentX());
        gf.setFurniCurrentZ(gfrDTO.getFurniCurrentZ());
        gf.setFurniRotate(gfrDTO.isFurniRotate());
        gf.setFurniCategory(gfrDTO.getFurniCategory());
        gf.setFurniName(gfrDTO.getFurniName());
        gf.setUserId(gfrDTO.getUserId());
        return repository.save(gf);
    }

    @Override
    public GroundFurniture getGroundFurnitureById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public GroundFurniture getGroundFurnitureByName(String name) {
        return repository.findByFurniName(name);
    }

    @Override
    public List<GroundFurniture> getAllGroundFurniture() {
        return repository.findAll();
    }

    @Override
    public List<GroundFurniture> getGroundFurnitureListByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<GroundFurniture> getGroundFurnitureListByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public void deleteGroundFurnitureById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteGroundFurnitureByName(String name) {
        repository.deleteByFurniName(name);
    }

    @Override
    public GroundFurniture updateGroundFurniture(GroundFurnitureUpdateDTO gfuDTO) {
        GroundFurniture foundG = repository.findById(gfuDTO.getId()).orElseThrow(() -> new IllegalArgumentException("not found g"));
        foundG.setFurniCategory(gfuDTO.getFurniCategory());
        foundG.setFurniName(gfuDTO.getFurniName());
        foundG.setFurniCurrentX(gfuDTO.getFurniCurrentX());
        foundG.setFurniCurrentZ(gfuDTO.getFurniCurrentZ());
        foundG.setOnPlace(gfuDTO.isOnPlace());
        foundG.setFurniSizeX(gfuDTO.getFurniSizeX());
        foundG.setFurniSizeZ(gfuDTO.getFurniSizeZ());
        foundG.setFurniRotate(gfuDTO.isFurniRotate());
        repository.save(foundG);
        return foundG;
    }

}
