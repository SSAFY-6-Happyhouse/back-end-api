package com.ssafy.happyhouse.interest.service;

import com.ssafy.happyhouse.interest.model.InterestRealtyDto;

import java.util.List;

public interface InterestRealtyService {
    public List<InterestRealtyDto> getInterestRealtyList(String username) throws Exception;
    public InterestRealtyDto saveInterestRealty(String username, Long realtyId) throws Exception;
    public void deleteInterestRealty(String username, Long realtyId) throws Exception;
}
