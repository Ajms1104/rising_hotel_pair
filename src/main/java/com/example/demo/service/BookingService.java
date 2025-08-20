package com.example.demo.service;

import com.example.demo.controller.dto.BookingResponseDto;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.entity.Booking;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class BookingService { //예약 취소 서비스
    private final BookingRepository bookingCancelRepository; //Repository

    @Transactional
    public BookingResponseDto cancelBooking(Integer bookingId, Integer userId) {

        // 예약 ID 못 찾을 경우
        Booking booking = bookingCancelRepository.findById(bookingId)
            .orElseThrow(() -> new IllegalArgumentException("해당 예약을 찾을 수 없습니다. ID: " + bookingId));

        //  예약자 본인 확인 로직
        if (!booking.getUser().getId().equals(userId)) {
            throw new SecurityException("예약을 취소할 권한이 없습니다.");
        }

        // 날짜 확인
        LocalDate today =  LocalDate.now();
        if(booking.getCheckIn().isBefore(today) || booking.getCheckIn().isEqual(today)){
            throw new IllegalStateException("예약 당일이거나 지난 예약은 취소 할 수 없습니다.");
        }

        // 모든 검증 통과 후
        booking.setStatus("CANCELLED");

        // 8. 성공 응답 DTO
        return BookingResponseDto.from(booking);
    }
}
