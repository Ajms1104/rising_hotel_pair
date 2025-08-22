package com.example.demo.controller;

import com.example.demo.controller.dto.BookingCancelResponseDto;
import com.example.demo.controller.dto.BookingRequestDto;
import com.example.demo.controller.dto.BookingResponseDto;
import com.example.demo.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)


public class BookingController {
    BookingService bookingService;

    //예약 취소 Controller | 담당 조아정
    @DeleteMapping("/{bookingId}") //ex : api/bookings/bookingId/123?userId=10
    public ResponseEntity<BookingCancelResponseDto> cancelBooking(
        @PathVariable //URL 경로에 있는 값은 매서드 파라미터로 매팅
        Integer bookingId,
        @RequestParam // ?{userId}=value
        Integer userId
        ){
        BookingCancelResponseDto responseDto = bookingService.cancelBooking(bookingId, userId);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<BookingResponseDto>create(@RequestBody BookingRequestDto request){
        BookingResponseDto booking = bookingService.reserve(request);
        return ResponseEntity.ok(booking);
    }

}
