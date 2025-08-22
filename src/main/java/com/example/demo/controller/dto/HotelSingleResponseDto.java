//담당: 조아정
package com.example.demo.controller.dto;

import com.example.demo.repository.entity.Hotel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class HotelSingleResponseDto { //호텔 단일(Single) 조회 응답 DTO
    //클라이언트가 단일 호텔 조회할 때 반환할 것들

    //1. 호텔의 상세 정보
    private Integer hotelId; //호텔 ID
    private String hotelName; //호텔 이름
    private String nation; //호텔이 있는 국가
    private String region; //호텔이 있는 지역
    private Double rating; //호텔의 평점

    //2. 해당 방의 예약가능 상태 및 가격
    private List<RoomDetailResponseDto> roomDetail;

    public static HotelSingleResponseDto from (Hotel entity, List<RoomDetailResponseDto> roomDetail) {
        return new HotelSingleResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getNation(),
            entity.getRegion(),
            entity.getRating(),
           roomDetail
        );
    }


//    public static HotelDetailResponseDto from(Hotel entity) {
//         Hotel → RoomType → Room → AvailableDate 평탄화 (FlatMap)
//        List<RoomDetailResponseDto> roomDetail = entity.getRoomTypes().stream()
//            .flatMap(roomType -> roomType.getRooms().stream()) // RoomType → Room
//            .flatMap(room -> room.getAvailableDates().stream()) // Room → AvailableDate
//            .map(RoomDetailResponseDto::from) // AvailableDate → RoomDetailResponseDto
//            .toList();
//        return new HotelDetailResponseDto(
//            entity.getId(),
//            entity.getName(),
//            entity.getNation(),
//            entity.getRegion(),
//            entity.getRating(),
//            roomDetail
//        );
    }


}
