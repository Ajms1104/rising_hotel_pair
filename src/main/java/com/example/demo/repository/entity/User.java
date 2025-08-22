package com.example.demo.repository.entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String name;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();

    public static User create(String username, String password, String name, String phone){
        return new User(
                  null,
                username,
                password,
                name,
                phone,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                Collections.emptyList()
        );

    }


    }

