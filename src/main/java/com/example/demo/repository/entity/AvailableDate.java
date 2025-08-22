
package com.example.demo.repository.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private Boolean isAvailable;
    private Integer price; // "어드민이 직접 입력"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    // 예약이 되면 booking_id가 채워짐
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public static AvailableDate create(LocalDate date, Boolean isAvailable, Integer price,Room room, Booking booking){
        return new AvailableDate(
                null,
                date,
                isAvailable,
                price,
                room,
                booking
        );
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}
