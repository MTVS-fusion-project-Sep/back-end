package com.mtvs.backend.roominfo.controller;

import com.mtvs.backend.roominfo.domain.RoomInfo;
import com.mtvs.backend.roominfo.dto.RoomInfoRegisterDTO;
import com.mtvs.backend.roominfo.dto.RoomInfoUpdateDTO;
import com.mtvs.backend.roominfo.service.RoomInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room-info")
public class RoomInfoController {

    private final RoomInfoServiceImpl roomInfoServiceImpl;

    @Autowired
    public RoomInfoController(RoomInfoServiceImpl roomInfoServiceImpl) {
        this.roomInfoServiceImpl = roomInfoServiceImpl;
    }

    @GetMapping
    public ResponseEntity<RoomInfo> getRoomInfoByUserId(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(roomInfoServiceImpl.getRoomInfoByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<RoomInfo> registerRoomInfo(@RequestBody RoomInfoRegisterDTO rirDTO) {
        return ResponseEntity.ok(roomInfoServiceImpl.registerRoomInfo(rirDTO));
    }

    @PatchMapping
    public ResponseEntity<RoomInfo> updateRoomInfo(@RequestBody RoomInfoUpdateDTO riuDTO) {
        return ResponseEntity.ok(roomInfoServiceImpl.updateRoomInfo(riuDTO));
    }

}
