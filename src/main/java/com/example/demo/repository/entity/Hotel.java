
package com.example.demo.repository.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double rating;
    private String region;
    private String nation;

    @OneToMany(mappedBy = "hotel")
    private List<RoomType> roomTypes = new ArrayList<>();
    // ✅ 정적 생성 메서드 추가
    public static Hotel create(String name, Double rating,String region, String nation) {
        return new Hotel(null, name, rating, region,nation,new ArrayList<>());
    }
}