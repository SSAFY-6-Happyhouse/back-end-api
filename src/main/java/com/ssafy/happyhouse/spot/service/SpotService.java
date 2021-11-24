package com.ssafy.happyhouse.spot.service;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SpotDto;
import com.ssafy.happyhouse.spot.entity.Segwon;
import com.ssafy.happyhouse.spot.entity.Spot;
import com.ssafy.happyhouse.spot.entity.SpotKeyword;
import org.locationtech.jts.io.ParseException;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.Optional;


public interface SpotService {
    public void saveSpot(SearchSpotCategoryRes searchSpotCategoryRes) throws Exception;
    public List<SpotDto> getSpotList(List<SpotKeyword> segwons) throws Exception;
    public SpotDto getSpot(Long spotId) throws Exception;
    public SpotDto updateSpot(SpotDto spotDto) throws Exception;
    public void deleteSpot(Long spotId) throws Exception;
    public List<Segwon> getSegwonList(double x, double y) throws Exception;
}
