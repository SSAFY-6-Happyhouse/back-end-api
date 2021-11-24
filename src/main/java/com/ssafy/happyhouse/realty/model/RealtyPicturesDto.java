package com.ssafy.happyhouse.realty.model;

import com.ssafy.happyhouse.realty.entity.RealtyPicture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealtyPicturesDto {
    private RealtyDto realtyDto;
    private List<RealtyPicture> realtyPictures;


}
