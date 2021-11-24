package com.ssafy.happyhouse.spot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.happyhouse.district.entity.Dong;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;


@Entity
@Table(name = "SPOTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Spot {
    @Id
    @Column(name = "SPOT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;

    //카테고리 코드
    @Column(name="SPOT_CATEGORY_CODE")
    private String categoryCode;

    //카테고리 이름
    @Column(name ="SPOT_CATEGORY_NAME")
    private String categoryName;

    //SPOT 이름
    @Column(name = "SPOT_NAME")
    private String spotName;

    //동
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "DONG_ID")
    private Dong dong;

    //상세주소
    @Column(name = "ADDRESS")
    private String address;

    //좌표
    @Column(name = "SPOT_POINT") //공간검색이좋다.
    private Point spotPoint; // Point 객체 안에 x,y가 있음


    //좌표
    @Column(name = "SPOT_X")
    private double x;

    @Column(name = "SPOT_Y")
    private double y;

}
