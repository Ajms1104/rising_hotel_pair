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

public class BookingController { //예약 취소 Controller
    private final BookingService bookingService;

    @DeleteMapping("/{bookingId}") //ex : api/bookings/bookingId/123
    public ResponseEntity<BookingCancelResponseDto> cancelBooking(
        @PathVariable
        Integer bookingId,
        @RequestParam
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
