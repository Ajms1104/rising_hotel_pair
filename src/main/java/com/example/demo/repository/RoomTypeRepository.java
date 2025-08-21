package com.example.demo.repository;

import com.example.demo.repository.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository <RoomType,Integer> {
    RoomType findByRoomTypes(String roomTypes);
    List<RoomType> findAllByRoomTypes(String roomTypes);
}
