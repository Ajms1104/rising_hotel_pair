package com.example.demo.repository;

import com.example.demo.repository.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailableDateRepository extends JpaRepository <AvailableDate,Integer>{
//    List<AvailableDate> findAllByAvailableDate(String availableDate);
//    List<AvailableDate> findAllByAvailableDateBetween(String availableDate1, String availableDate2);
//    List<AvailableDate> findAllByRoomId(Integer roomId);
//    List<AvailableDate> findAllByBookingId(Integer bookingId);
//    List<AvailableDate> findAllByIsAvailableTrue(Boolean isAvailable);

    /*
    SELECT *
    FROM available_date
    WHERE room_id IN ( ... )
    AND date = ?
    AND is_available = true;
     */
    List<AvailableDate> findByRoomIdInAndDateAndIsAvailableTrue(List<Integer> roomIds, LocalDate date);
}