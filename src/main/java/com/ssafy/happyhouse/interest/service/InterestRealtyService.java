package com.ssafy.happyhouse.interest.service;

import com.ssafy.happyhouse.interest.model.InterestRealtyDto;
import com.ssafy.happyhouse.realty.entity.Realty;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterestRealtyService {
    public List<Realty> getInterestRealtyList(String username, Pageable pageable) throws Exception;
    public InterestRealtyDto saveInterestRealty(String username, Long realtyId) throws Exception;
    public void deleteInterestRealty(String username, Long realtyId) throws Exception;
}
