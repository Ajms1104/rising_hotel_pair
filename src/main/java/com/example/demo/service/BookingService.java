package com.example.demo.service;

import com.example.demo.controller.dto.BookingCancelResponseDto;
import com.example.demo.controller.dto.BookingRequestDto;
import com.example.demo.controller.dto.BookingResponseDto;
import com.example.demo.repository.*;
import com.example.demo.repository.entity.*;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Setter
@Service
@RequiredArgsConstructor

public class BookingService { //예약 취소 서비스
    private final BookingRepository bookingRepository; //Repository
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final AvailableDateRepository availableDateRepository;

    @Transactional
    public BookingCancelResponseDto cancelBooking(Integer bookingId, Integer userId) {

        // 예약 ID 못 찾을 경우
        Booking booking = bookingRepository.findById(bookingId)
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
        return BookingCancelResponseDto.from(booking);
    }

    // 예약 하기
    @Transactional
    public BookingResponseDto reserve(BookingRequestDto request){
        // userId 조회
        Integer userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("데이터베이스 내 userId가 존재하지 않습니다. userId :" + userId ));

        // hotelId 조회
        Integer hotelId = request.getHotelId();
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("데이터베이스 내 hotelId가 존재하지 않습니다. hotelId :" + hotelId));

        // roomId 조회
        Integer roomNumber = request.getRoomNumber();
        Integer roomTypeId = request.getRoomTypeId();
        Room room = roomRepository.findByRoomNumberAndRoomTypeId(roomNumber,roomTypeId)
                .orElseThrow(() -> new RuntimeException("데이터베이스 내 roomId가 존재하지 않습니다. 방호수 :" + roomNumber));
        //roomTypeId 조회
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("데이터베이스 내 roomTypeId가 존재하지 않습니다. hotelId :" + roomTypeId));

        // AvailableDate 조회 (달력 등록 해놨을 경우 조회 예약간으 여부는 따로)
        AvailableDate availableDate = availableDateRepository.findByRoomAndDate(room,request.getBookingDate())
                .orElseThrow(()->new RuntimeException("해당 날짜에 방이 없습니다."));


        // 에약가능 여부 확인
        if(!availableDate.getIsAvailable()){
            throw new RuntimeException("이미 예약된 방입니다.");
        }

        // Booking 객체 생성하기
        Booking booking = Booking.create(
                request.getGuestNumber(),
                request.getCheckIn(),
                request.getCheckOut(),
                request.getIsWalkIn(),
                request.getSpecialRequests(),
                user,
                hotel
        );



        availableDate.setBooking(booking);
        // 양방향 매핑 시 Booking에도 연결
        booking.getReservedDates().add(availableDate);

        Booking created = bookingRepository.save(booking);
        return BookingResponseDto.from(created);
    }
}
