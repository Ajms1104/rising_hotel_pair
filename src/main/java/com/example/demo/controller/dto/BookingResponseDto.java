package com.example.demo.controller.dto;

import com.example.demo.repository.entity.AvailableDate;
import com.example.demo.repository.entity.Booking;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor

public class BookingResponseDto {

    private Integer reservationId;
    private LocalDate bookingDate;
    private Integer hotelId;
    private Integer guestNumber;
    private Boolean isWalkIn;
    private String specialRequests;
    private List<RoomResponseDto> rooms;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer userId;
    private String status;

    public static BookingResponseDto from(Booking booking) {
        List<AvailableDate> reservedDates = booking.getReservedDates();
        List<RoomResponseDto> rooms = reservedDates.stream()
            .map(RoomResponseDto::from)
            .toList();
        return new BookingResponseDto(
            booking.getId(), // 예약 아이디
            booking.getBookingDate(), // 예약일
            booking.getHotel().getId(), // 호텔 Id
            booking.getGuestNumber(),  // 총 인원 수
            booking.getIsWalkIn(),   // 도보방문
            booking.getSpecialRequests(), // 요청사항
            rooms, // roomNumber, roomType, price
            booking.getCheckIn(),
            booking.getCheckOut(),
            booking.getUser().getId(), // user 아이디
            booking.getStatus()// 예약완료 반환
        );
    }
}
