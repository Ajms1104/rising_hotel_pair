//담당 : 조아정
package com.example.demo.controller.dto;

import com.example.demo.repository.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingCancelResponseDto { //예약 취소 응답 Dto
    //클라이언트에게 예약 취소 상태 반환할 때 사용할 것
    private Integer bookingId; //예약 ID
    private Integer userId; //유저 ID
    private String status; //예약 상태


    public static BookingCancelResponseDto from (Booking entity){
        return new BookingCancelResponseDto(
            entity.getId(),
            entity.getUser().getId(),
            entity.getStatus()
        );
    }
}
