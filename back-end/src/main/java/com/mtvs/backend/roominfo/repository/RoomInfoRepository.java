package com.mtvs.backend.roominfo.repository;

import com.mtvs.backend.roominfo.domain.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {

    @Query("SELECT ri FROM RoomInfo ri WHERE ri.userId = :userId")
    RoomInfo getRoomInfoByUserId(@Param("userId") String userId);
}
