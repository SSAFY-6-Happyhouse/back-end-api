package com.ssafy.happyhouse.realty.model;

import com.ssafy.happyhouse.realty.entity.Realty;
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
    private String fileName;
    private String origFilename;
    private String location;
    private Long realty_id;
    public RealtyPicture toEntity(){
        return RealtyPicture.builder().location(location).build();
    }
}
