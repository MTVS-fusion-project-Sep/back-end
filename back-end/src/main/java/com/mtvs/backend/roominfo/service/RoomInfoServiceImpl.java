package com.mtvs.backend.roominfo.service;

import com.mtvs.backend.roominfo.domain.RoomInfo;
import com.mtvs.backend.roominfo.dto.RoomInfoRegisterDTO;
import com.mtvs.backend.roominfo.dto.RoomInfoUpdateDTO;
import com.mtvs.backend.roominfo.repository.RoomInfoRepository;
import com.mtvs.backend.user.domain.User;
import com.mtvs.backend.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomInfoServiceImpl implements RoomInfoService{

    private final UserServiceImpl userServiceImpl;
    private final RoomInfoRepository roomInfoRepository;

    @Autowired
    public RoomInfoServiceImpl(UserServiceImpl userServiceImpl, RoomInfoRepository roomInfoRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roomInfoRepository = roomInfoRepository;
    }

    @Override
    public RoomInfo registerRoomInfo(RoomInfoRegisterDTO rirDTO) {
        RoomInfo roomInfo = new RoomInfo(rirDTO.getWallIndex(), rirDTO.getWallIndex(), rirDTO.getUserId());
        return roomInfoRepository.save(roomInfo);
    }

    @Override
    public RoomInfo updateRoomInfo(RoomInfoUpdateDTO riuDTO) {

        RoomInfo foundRoomInfo = roomInfoRepository.findById(riuDTO.getId()).orElseThrow(() -> new IllegalArgumentException("not found roomFInfo id: " + riuDTO.getId()));
        foundRoomInfo.setTileIndex(riuDTO.getTileIndex());
        foundRoomInfo.setWallIndex(riuDTO.getWallIndex());
        return roomInfoRepository.save(foundRoomInfo);

    }

    @Override
    public void deleteRoomInfoByRoomInfoId(long roomInfoId) {
        roomInfoRepository.deleteById(roomInfoId);
    }

    @Override
    public RoomInfo getRoomInfoByUserId(String userId) {
        return roomInfoRepository.getRoomInfoByUserId(userId);
    }
}
