package com.example.demo.controller.dto;

import com.example.demo.repository.entity.AvailableDate;
import lombok.Getter;

@Getter
public class RoomResponseDto {
    private Integer roomNumber;
    private String roomType;
    private Integer price;

    public RoomResponseDto(Integer roomNumber, String roomType, Integer price){
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
    }

    public static RoomResponseDto from(AvailableDate availableDate){
        return new RoomResponseDto(
                availableDate.getRoom().getRoomNumber(),
                availableDate.getRoom().getRoomType().getRoomTypes(),
                availableDate.getPrice()
        );
    }
}

