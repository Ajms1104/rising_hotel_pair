package com.example.demo.service;

import com.example.demo.controller.dto.HotelDetailResponseDto;
import com.example.demo.repository.HotelRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    //단일 호텔 조회 관련
    @Transactional
    public HotelDetailResponseDto getHotelDetail(Integer hotelId, LocalDate date) {

    }
}
