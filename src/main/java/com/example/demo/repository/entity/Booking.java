
package com.example.demo.repository.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //자동 채워주기? 그런 거래요
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor

public class Booking {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer id;

    private LocalDate bookingDate;
    private String status;
    private Integer guestNumber;
    private LocalTime eta;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Boolean isWalkIn;
    private String specialRequests;


    //예약 자체 생성일
    private LocalDateTime createdAt;
    //예약 자체 수정일
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //FK
    private User user;

    // 호텔 연결
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="hotel_id")
    private Hotel hotel;


    // 예약이 잡은 날짜들(available_date.booking_id 로 연결)
    @OneToMany(mappedBy = "booking")
    private List<AvailableDate> reservedDates = new ArrayList<>();


    public static Booking create(Integer guestNumber, LocalDate checkIn,LocalDate checkOut,Boolean isWalkIn, String specialRequests,User user, Hotel hotel){
        return new Booking(
                null,
                LocalDate.now(),
                "CONFIRMED",
                guestNumber,
                LocalTime.now(),
                checkIn,
                checkOut,
                isWalkIn,
                specialRequests,
                LocalDateTime.now(),
                LocalDateTime.now(),
                user,
                hotel,
                Collections.emptyList()
        );
    }
}
