package com.example.demo.repository;

import com.example.demo.repository.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking findByBookingId(Integer bookingId);
    Booking findByUserId(Integer userId);
    List<Booking> findAllByUserId(Integer userId);
    List<Booking> findAllByCheckInDateBetween(String checkInDate1, String checkInDate2);
    List<Booking> findAllByCheckOutDateBetween(String checkOutDate1, String checkOutDate2);
    List<Booking> findAllByStatus(String status);

}
