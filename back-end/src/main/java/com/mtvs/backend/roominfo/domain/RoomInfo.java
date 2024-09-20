package com.mtvs.backend.roominfo.domain;


import com.mtvs.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="room_info")
@Getter @Setter
public class RoomInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="wall_index")
    int wallIndex;

    @Column(name="tile_index")
    int tileIndex;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    public RoomInfo() {
    }

    public RoomInfo(int wallIndex, int tileIndex, User user) {
        this.wallIndex = wallIndex;
        this.tileIndex = tileIndex;
        this.user = user;
    }
}
