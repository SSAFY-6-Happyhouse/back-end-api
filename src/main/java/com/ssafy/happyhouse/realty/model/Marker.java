package com.ssafy.happyhouse.realty.model;

import com.ssafy.happyhouse.realty.entity.ContractType;
import com.ssafy.happyhouse.realty.entity.RealtyType;
import com.ssafy.happyhouse.spot.entity.Segwon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Marker {
    private String imgPath;
    private Long price;
    private ContractType contractType;
    private RealtyType realtyType;
    private List<Segwon> segwons;
    private Double latitude;
    private Double longitude;
}
