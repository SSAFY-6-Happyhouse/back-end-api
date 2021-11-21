package com.ssafy.happyhouse.district.entity;

import javax.persistence.*;

@Entity
@Table(name = "GUGUN")
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
