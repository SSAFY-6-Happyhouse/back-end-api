package com.ssafy.happyhouse.realty.model;

import com.ssafy.happyhouse.spot.entity.Segwon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class realty_segwonsDto {
    private RealtyDto realtyDto;
    private List<Segwon> segwons ;
}
