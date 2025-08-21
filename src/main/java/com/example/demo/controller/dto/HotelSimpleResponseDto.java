package com.example.demo.controller.dto;

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

    public static HotelSimpleResponseDto unavailable(Integer hotelId, String name, String region, String nation, Double rating) {
        return new HotelSimpleResponseDto(
                hotelId, name, region, nation, rating,
                false, null, null, null);
    }

    public static HotelSimpleResponseDto available(Integer hotelId, String name, String region, String nation, Double rating, Integer roomTypeId, String roomTypeName, Integer price) {
        return new HotelSimpleResponseDto(hotelId, name, region, nation, rating,
                true, roomTypeId, roomTypeName, price);
}
}
