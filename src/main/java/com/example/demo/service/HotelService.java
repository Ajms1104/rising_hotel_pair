package com.example.demo.service;

import com.example.demo.repository.AvailableDateRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomTypeRepository;
import com.example.demo.repository.entity.AvailableDate;
import com.example.demo.repository.entity.Hotel;
import com.example.demo.repository.entity.Room;
import com.example.demo.repository.entity.RoomType;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.controller.dto.HotelSimpleResponseDto;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;
    private final AvailableDateRepository availableDateRepository;

    /*TODO: 전체 호텔 간단 정보 조회 API
    1. 전체 호텔을 조회한다
    2. 요청한 날짜에 가능한 각 호텔의 RoomType, Type별 Room들을 조회
    3. 최저가 1개 반환
    4. 해당 날짜에 가능한 방이 없으면 dto 반환
    */
    @Transactional
    public List<HotelSimpleResponseDto> getHotels(LocalDate date) {
        List<HotelSimpleResponseDto> result = new ArrayList<>();
        //1. 전체 호텔을 조회한다
        List<Hotel> hotels = hotelRepository.findAll();

        //2. 요청한 날짜에 가능한 각 호텔의 RoomType, Type별 Room들을 조회
        for (Hotel hotel : hotels) {
            // 각 호텔마다 새로운 roomIds 리스트 생성
            List<Integer> roomIds = new ArrayList<>();

            List<RoomType> roomTypes = hotel.getRoomTypes();
            for (RoomType roomType : roomTypes) {
                List<Room> rooms = roomType.getRooms();
                for (Room room : rooms) {
                    roomIds.add(room.getId());
                }
            }

            // 해당 날짜에 룸이 없을 경우
            if (roomIds.isEmpty()) {
                result.add(HotelSimpleResponseDto.unavailable(hotel));
                continue; // 다음 호텔로 진행
            }

            // 룸 있을 경우 예약 가능 날짜 조회
            List<AvailableDate> availableDates = availableDateRepository
                    .findByRoomIdInAndDateAndIsAvailableTrue(roomIds, date);

            // 가능한 날짜가 없는 경우
            if (availableDates.isEmpty()) {
                result.add(HotelSimpleResponseDto.unavailable(hotel));
                continue; // 다음 호텔로 진행
            }

            // RoomType별 최저가
            Map<Integer, AvailableDate> minByRoomType = new HashMap<>();

            for (AvailableDate availableDate : availableDates) {
                Integer typeId = availableDate.getRoom().getRoomType().getId();
                AvailableDate currentMin = minByRoomType.get(typeId);
                // 해당 타입의 첫 번째 방이거나 더 저렴한 가격을 찾은 경우
                if (currentMin == null || availableDate.getPrice() < currentMin.getPrice()) {
                    minByRoomType.put(typeId, availableDate);
                }
            }

            // 타입별 최저가 중에서 전체 최저가 찾기
            if (minByRoomType.isEmpty()) {
                // 예상치 못한 상황이지만 방어 코드 추가
                result.add(HotelSimpleResponseDto.unavailable(hotel));
                continue;
            }

            AvailableDate overallMin = null;
            for (AvailableDate candidate : minByRoomType.values()) {
                if (overallMin == null || candidate.getPrice() < overallMin.getPrice()) {
                    overallMin = candidate;
                }
            }

            // 최종 응답 생성
            result.add(HotelSimpleResponseDto.available(hotel, overallMin));
        }

        return result;
    }
}
