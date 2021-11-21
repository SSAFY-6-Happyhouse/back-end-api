package com.ssafy.happyhouse.district.entity;

import javax.persistence.*;

@Entity
@Table(name = "SIDO")
public class Sido {
    @Id
    @Column(name = "SIDO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sidoId;

    @Column(name = "SIDOCODE")
    private String sidoCode;

    @Column(name = "SIDONAME")
    private String sidoName;
}
