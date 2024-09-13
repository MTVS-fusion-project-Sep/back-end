package com.mtvs.backend.roominfo.service;

import com.mtvs.backend.roominfo.domain.RoomInfo;
import com.mtvs.backend.roominfo.dto.RoomInfoRegisterDTO;
import com.mtvs.backend.roominfo.dto.RoomInfoUpdateDTO;

public interface RoomInfoService {

    RoomInfo registerRoomInfo(RoomInfoRegisterDTO rirDTO);
    RoomInfo updateRoomInfo(RoomInfoUpdateDTO riuDTO);
    void deleteRoomInfoByRoomInfoId(long roomInfoId);
    RoomInfo getRoomInfoByUserId(String userId);

}
