package com.ssafy.happyhouse.spot.service;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SpotDto;
import com.ssafy.happyhouse.spot.entity.Spot;

import java.util.List;
import java.util.Optional;


public interface SpotService {
    public void saveSpot(SearchSpotCategoryRes searchSpotCategoryRes);
    public List<SearchSpotCategoryRes> getSpotList();
    public Spot getSpot(Long spotId);
    public SearchSpotCategoryRes updateSpot(SearchSpotCategoryRes searchSpotCategoryRes);
    public void deleteSpot(Long spotId);

}
