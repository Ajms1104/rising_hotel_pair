package com.example.demo.controller.dto;

import com.example.demo.repository.entity.AvailableDate;
import com.example.demo.repository.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HotelSimpleResponseDto {
// 호텔의 간단한 정보 리스트로 반환
// 해당 날짜에 예약 가능한 가장 저렴한 객실타입, 가격 1개만 반환
// 해당 날짜에 예약 객실 타입이 없으면 예약 불가 여부와 함께 호텔 정보 반환
    private Integer hotelId ;
    private String hotelName;
    private String nation;
    private String region;
    private Double rating;
    private Boolean available;
    private Integer cheapestRoomTypeId; //nullable
    private String cheapestRoomTypeName; //nullable
    private Integer Price; //nullable

    public static HotelSimpleResponseDto unavailable(Hotel hotel) {
        return new HotelSimpleResponseDto(
                hotel.getId(), hotel.getName(), hotel.getNation(), hotel.getRegion(), hotel.getRating(), 
                false,
                null,
                null,
                null
        );
    }

    public static HotelSimpleResponseDto available(Hotel hotel, AvailableDate availableDate) {
        return new HotelSimpleResponseDto(
                hotel.getId(), hotel.getName(), hotel.getNation(), hotel.getRegion(), hotel.getRating(),
                true,
                availableDate.getRoom().getRoomType().getId(),
                availableDate.getRoom().getRoomType().getRoomTypes(),
                availableDate.getPrice()
        );
}
}
