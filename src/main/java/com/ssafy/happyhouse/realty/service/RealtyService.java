package com.ssafy.happyhouse.realty.service;

import com.ssafy.happyhouse.realty.model.RealtyDto;

import java.util.List;

public interface RealtyService {
    public String saveRealty(RealtyDto realtyDto,String username);
    public RealtyDto updatePost(RealtyDto realtyDto);
    public void deleteRealty(Long realtyId);
    public RealtyDto getRealty(Long realtyId);
    public List<RealtyDto> getRealtyList();
}
