
package com.example.demo.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer roomNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    private List<AvailableDate> availableDates = new ArrayList<>();

    public static Room create(Integer roomNumber, RoomType roomType) {
        Room room = new Room(
                null,
                roomNumber,
                roomType,
                new ArrayList<>() //  수정 가능한 리스트
        );
        roomType.getRooms().add(room); //  양방향 관계 등록 (주인이 설정)
        return room;
    }
}
