package com.example.demo.controller;


import com.example.demo.controller.dto.BookingResponseDto;
import com.example.demo.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BookingController { //예약 취소 Controller
    BookingService bookingCancelService;

    @DeleteMapping("/{bookingId}") //ex : api/bookings/bookingId/123
    public ResponseEntity<BookingResponseDto> cancelBooking(
        @PathVariable
        Integer bookingId,
        @RequestParam
        Integer userId
        ){
        BookingResponseDto responseDto = bookingCancelService.cancelBooking(bookingId, userId);
        return ResponseEntity.ok(responseDto);
    }


}
