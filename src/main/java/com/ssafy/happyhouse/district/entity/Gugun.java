package com.ssafy.happyhouse.district.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "GUGUN")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gugun {
    @Id
    @Column(name = "GUGUN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gugunId;

    @Column(name = "GUGUNCODE")
    private String gugunCode;

    @Column(name = "GUGUNNAME")
    private String gugunName;
}
