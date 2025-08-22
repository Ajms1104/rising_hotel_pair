package com.example.demo.service;

import com.example.demo.controller.dto.HotelSingleResponseDto;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.RoomTypeRepository;
import com.example.demo.repository.entity.Hotel;
import com.example.demo.repository.entity.RoomType;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;

    //단일 호텔 조회 관련
    @Transactional
    public HotelSingleResponseDto getHotelSingleDetail(Integer hotelId, LocalDate date) {

        Hotel hotel = hotelRepository.findById(hotelId)
            // 호텔 ID 못 찾을 경우
            .orElseThrow(() -> new IllegalArgumentException("해당 호텔을 찾을 수 없습니다. ID: " + hotelId));

        //호텔에 속한 객실 타입 조회
        List<RoomType> roomTypes = roomTypeRepository.findByhotel_Id(hotelId);

        //날짜별 예약 현황
        boolean available = roomType.getTotalRooms() >
    }
}
