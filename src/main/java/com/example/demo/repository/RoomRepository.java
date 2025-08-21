package com.example.demo.repository;

import com.example.demo.repository.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository <Room,Integer>{
    Room findByRoomNumber(Integer roomNumber);
    List<Room> findAllByRoomNumber(Integer roomNumber);
    Room findByRoomTypeId(Integer roomTypeId);
    List<Room> findAllByRoomTypeId(Integer roomTypeId);
}
