package com.example.demo.init;


import com.example.demo.repository.*;
import com.example.demo.repository.entity.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.example.demo.repository.entity.QBooking.booking;
@Setter
@Component
@RequiredArgsConstructor
public class InitData {
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final AvailableDateRepository availableDateRepository;

    @PostConstruct
    public void init() {
        Hotel hotel = hotelRepository.save(Hotel.create("신라호텔", 4.5, "서울", "대한민국"));
        RoomType roomType = roomTypeRepository.save(RoomType.create("standard", hotel));
        Room room = roomRepository.save(Room.create(101, roomType)); // ✅ 이제 room까지 저장됨
        roomType.getRooms().add(room);  // ✅ 중요! 객체 그래프에서도 연결
        User user = userRepository.save(User.create("testuser", "1234", "홍길동", "010-1234-5678"));

        AvailableDate availableDate = AvailableDate.create(
                LocalDate.of(2025,8,22),  // 날짜
                true,                       // 예약 가능 여부
                100000,                     // 가격
                room,
                null// 연결된 room
        );
        room.getAvailableDates().add(availableDate); // ✅ 필수
        availableDateRepository.save(availableDate);
    }
}

