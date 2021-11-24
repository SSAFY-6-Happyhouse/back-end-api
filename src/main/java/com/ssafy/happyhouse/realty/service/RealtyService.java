package com.ssafy.happyhouse.realty.service;

import com.ssafy.happyhouse.realty.entity.RealtyPicture;
import com.ssafy.happyhouse.realty.model.RealtyDto;
import com.ssafy.happyhouse.realty.model.RealtyPicturesDto;
import com.ssafy.happyhouse.realty.model.RealtyResponseDto;

import java.util.List;

public interface RealtyService {
    public String saveRealty(RealtyDto realtyDto,String username);
    public RealtyDto updatePost(RealtyDto realtyDto);
    public void deleteRealty(Long realtyId);
    public RealtyResponseDto getRealty(Long realtyId);
    public List<RealtyDto> getRealtyList();
}
