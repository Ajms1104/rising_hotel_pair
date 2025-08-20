<<<<<<<< HEAD:src/main/java/com/example/demo/repository/entity/User.java
package com.example.demo.repository.entity;
========
package com.example.demo.repository.entitiy;
>>>>>>>> origin/main:src/main/java/com/example/demo/repository/entitiy/User.java

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    }

