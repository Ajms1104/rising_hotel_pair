package com.example.demo.repository;

import com.example.demo.repository.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository <Room,Integer>{
//    Room findByRoomNumber(Integer roomNumber);
//    List<Room> findAllByRoomNumber(Integer roomNumber);
//    Room findByRoomTypeId(Integer roomTypeId);
//    List<Room> findAllByRoomTypeId(Integer roomTypeId);
}
