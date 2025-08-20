package com.example.demo.repository;

import com.example.demo.repository.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingCancelRepository extends JpaRepository<Booking,Integer> {

}
