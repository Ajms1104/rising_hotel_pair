//담당 : 조아정
package com.example.demo.controller.dto;

import com.example.demo.repository.entity.AvailableDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomDetailResponseDto {
    //단일 조회시 예약 날짜 및 객실 타입 반환하기 (in Availability)
        private Integer availabilityId; //예약 가능 ID
        private Boolean availableStatus; //예약 가능 상태

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate date;// 날짜
        private Integer price; // 가격
        private String roomType; // 객실 타입

    public static  RoomDetailResponseDto from (AvailableDate entity){
        return new RoomDetailResponseDto(
            entity.getId(),
            entity.getIsAvailable(),
            entity.getDate(),
            entity.getPrice(),
            entity.getRoom().getRoomType().getRoomType()
        );
    }
}
