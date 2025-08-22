package com.example.demo.repository;

import com.example.demo.repository.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
//    Hotel findByHotelId(Integer hotelId);
//    List<Hotel> findAllByHotelId(Integer hotelId);
//    Hotel findByName(String name);
//    Hotel findByRegion(String region);
//    Hotel findByNation(String nation);
//    Hotel findByRating(Double rating);
}
