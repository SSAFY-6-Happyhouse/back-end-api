package com.ssafy.happyhouse.district.entity;

import javax.persistence.*;

@Entity
@Table(name = "DISTRICTS")
public class District {
    @Id
    @Column(name = "DISTRICT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;
/*
    private Sido sido;

    private Gugun gugun;

    private Dong dong;*/



}
