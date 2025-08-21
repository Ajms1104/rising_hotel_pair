package com.example.demo.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookingRequestDto {
    private LocalDate bookingDate; // 예약날짜
    private Integer hotelId;
    private Integer roomTypeId;  // 객실 타입
    private Integer roomNumber; // 호수
    private Integer userId;
    private Integer guestNumber;
    private Boolean isWalkIn;
    private String specialRequests;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalTime eta;
}
