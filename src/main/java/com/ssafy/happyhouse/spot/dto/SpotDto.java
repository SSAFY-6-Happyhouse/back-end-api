package com.ssafy.happyhouse.spot.dto;

import com.ssafy.happyhouse.district.entity.Dong;
import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Data
@Builder
public class SpotDto {
    private String categoryCode;
    private String categoryName;
    private String spotName;
    private Dong dong;
    private String address;
    private Point spotPoint;
    private double x;
    private double y;
}
