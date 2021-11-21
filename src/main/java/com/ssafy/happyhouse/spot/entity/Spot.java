package com.ssafy.happyhouse.spot.entity;

import com.ssafy.happyhouse.district.entity.Dong;
import lombok.Data;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "SPOTS")
@Data
public class Spot {
    @Id
    @Column(name = "SPOT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;

    @Column(name = "SPOT_NAME")
    private String spotName;

    @Column(name = "SPOT_KEYWORD")
    private String spotKeyword;

    //동
    @ManyToOne
    @JoinColumn(name = "DONG_ID")
    private Dong dong;

    //상세주소
    @Column(name = "ADDRESS")
    private String address;

    //좌표
    @Column(name = "SPOT_POINT")
    private Point spotPoint;
}
