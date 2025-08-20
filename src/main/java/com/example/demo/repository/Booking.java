package com.example.demo.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Booking {

    @Id
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 예약이 잡은 날짜들(available_date.booking_id 로 연결)
    @OneToMany(mappedBy = "booking")
    private List<AvailableDate> reservedDates = new ArrayList<>();
}
