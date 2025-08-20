<<<<<<<< HEAD:src/main/java/com/example/demo/repository/entity/AvailableDate.java
package com.example.demo.repository.entity;
========
package com.example.demo.repository.entitiy;
>>>>>>>> origin/main:src/main/java/com/example/demo/repository/entitiy/AvailableDate.java

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private Boolean isAvailable;
    private Integer price; // "어드민이 직접 입력"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private String room;

    // 예약이 되면 booking_id가 채워짐
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
