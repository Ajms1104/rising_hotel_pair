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
    List<AvailableDate> findAllByAvailableDate(String availableDate);
    List<AvailableDate> findAllByAvailableDateBetween(String availableDate1, String availableDate2);
    List<AvailableDate> findAllByRoomId(Integer roomId);
    List<AvailableDate> findAllByBookingId(Integer bookingId);
    List<AvailableDate> findAllByIsAvailableTrue(Boolean isAvailable);

    //  예약 확인용
    Optional<AvailableDate> findByRoomAndDate(Room room, LocalDate date);

}