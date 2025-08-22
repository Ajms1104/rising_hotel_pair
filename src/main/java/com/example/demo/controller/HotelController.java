package com.example.demo.controller;

import com.example.demo.controller.dto.HotelSingleResponseDto;
import com.example.demo.controller.dto.HotelSimpleResponseDto;
import com.example.demo.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    // 1. 전체 호텔 간단 정보 조회 API
    @GetMapping("")
    public ResponseEntity<List<HotelSimpleResponseDto>> getHotels(
            @RequestParam LocalDate date
    ) {
        List<HotelSimpleResponseDto> hotels = hotelService.getHotels(date);
        return ResponseEntity.ok(hotels);
    }

    // 2. 단일 호텔 상세 정보 조회 API
        @GetMapping("/{hotelId}") //ex : api/hotels/hotelId/456?date=2025-08-22
        public ResponseEntity<HotelSingleResponseDto> getHotelSingleDetail(
            @PathVariable
                Integer hotelId,
            @RequestParam // ?{date}=value
                LocalDate date //이렇게만 해도 되나..?
    ) {
            HotelSingleResponseDto hotel = hotelService.getHotelSingleDetail(hotelId, date);
            return ResponseEntity.ok(hotel);
    }
}
