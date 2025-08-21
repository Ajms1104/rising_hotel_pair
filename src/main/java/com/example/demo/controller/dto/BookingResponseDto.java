package com.example.demo.controller.dto;


import com.example.demo.repository.entity.AvailableDate;
import com.example.demo.repository.entity.Booking;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class BookingResponseDto {

    private Integer reservationId;

    private LocalDate bookingDate;
    private Integer hotelId;
    private List<RoomResponseDto> rooms;
    private Integer userId;
    private String status;

// 복습
    public static BookingResponseDto from(Booking booking) {
        List<AvailableDate> reservedDates = booking.getReservedDates();
        List<RoomResponseDto> rooms = reservedDates.stream()
                .map(RoomResponseDto::from)
                .toList();

        return new BookingResponseDto(
                booking.getId(), // 예약 아이디
                booking.getBookingDate(), // 예약일
                booking.getHotel().getId(), // 호텔 Id
                rooms, // roomNumber, roomType, price
                booking.getUser().getId(), // user 아이디
                booking.getStatus() // 예약완료 반환
        );
    }
}
