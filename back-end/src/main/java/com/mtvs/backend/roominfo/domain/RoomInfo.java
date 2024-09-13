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

    @Column(name="wallpaper_name")
    String wallpaperName;

    @Column(name="tile_name")
    String tileName;

    @ManyToOne
    @JoinColumn(name="user_id")
    User user;

    public RoomInfo() {
    }

    public RoomInfo(String wallpaperName, String tileName, User user) {
        this.wallpaperName = wallpaperName;
        this.tileName = tileName;
        this.user = user;
    }
}
