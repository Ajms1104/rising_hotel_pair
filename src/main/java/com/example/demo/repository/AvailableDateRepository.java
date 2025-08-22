package com.example.demo.repository;

import com.example.demo.repository.entity.AvailableDate;
import com.example.demo.repository.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository <AvailableDate,Integer>{
    /*
    SELECT *
    FROM available_date
    WHERE room_id IN ( ... )
    AND date = ?
    AND is_available = true;
     */
    List<AvailableDate> findByRoomIdInAndDateAndIsAvailableTrue(List<Integer> roomIds, LocalDate date);

    //  예약 확인용
    Optional<AvailableDate> findByRoomAndDate(Room room, LocalDate date);

}