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
    public void saveSpot(SearchSpotCategoryRes searchSpotCategoryRes) throws ParseException;
    public List<SpotDto> getSpotList(List<SpotKeyword> segwons);
    public SpotDto getSpot(Long spotId);
    public SpotDto updateSpot(SpotDto spotDto);
    public void deleteSpot(Long spotId);
    public List<Segwon> getSegwonList(Point point) throws ParseException;
}
