//담당: 조아정
package com.example.demo.controller.dto;

import com.example.demo.repository.entity.AvailableDate;
import com.example.demo.repository.entity.Hotel;
import com.example.demo.repository.entity.RoomType;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class HotelDetailResponseDto { //호텔 단일(Single) 조회 응답 DTO
    //클라이언트가 단일 호텔 조회할 때 반환할 것들

    //1. 호텔의 상세 정보
    private Integer hotelId; //호텔 ID
    private String hotelName; //호텔 이름
    private String nation; //호텔이 있는 국가
    private String region; //호텔이 있는 지역
    private Double rating; //호텔의 평점

    //2. 해당 방의 예약가능 상태 및 가격
    private List<RoomDetailResponseDto> roomDetail;

    public static HotelDetailResponseDto from (Hotel entity){
        List<RoomTypes> roomTypes = entity.getRoomTypes();
        List<AvailableDate> availableDates = 
        List<RoomDetailResponseDto> roomDetail

        return new HotelDetailResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getNation(),
            entity.getRegion(),
            entity.getRating(),
            roomDetail
        );
    }

}
