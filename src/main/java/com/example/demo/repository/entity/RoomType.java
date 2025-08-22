package com.example.demo.repository.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class RoomType {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roomTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms = new ArrayList<>();
    // mappedBy 사용시 DB에서 관리 x 메모리 상 연결만 해줌

    public static RoomType create(String roomTypes, Hotel hotel){
        return new RoomType(
                null,
                roomTypes,
                hotel,
                new ArrayList<>()
        );
    }
}