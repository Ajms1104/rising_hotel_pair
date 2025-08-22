package com.example.demo.repository;

import com.example.demo.repository.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomTypeRepository extends JpaRepository <RoomType,Integer> {
    RoomType findByRoomTypes(String roomTypes);
    List<RoomType> findAllByRoomTypes(String roomTypes);
    List<RoomType> findByhotel_Id(Integer hotelId);
}
