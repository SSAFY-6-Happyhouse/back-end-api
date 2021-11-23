package com.ssafy.happyhouse.spot.service;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SpotDto;
import com.ssafy.happyhouse.spot.entity.Spot;
import com.ssafy.happyhouse.spot.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService{

    private final SpotRepository spotRepository;
    //private final DongRepository dongRepository;

    @Override
    public void saveSpot(SearchSpotCategoryRes searchSpotCategoryRes) {
        //동구하기
        String[] str=searchSpotCategoryRes.getAddressName().split(" ");
        //Dong dong = dongRepository.findByDongName(str[2]);


        //spotRepository.save(searchSpotCategoryRes);
        //Spot spot=
                spotRepository.save(Spot.builder()
                        .categoryCode(searchSpotCategoryRes.getCategoryGroupCode())
                .categoryName(searchSpotCategoryRes.getCategoryGroupName())
                .spotName(searchSpotCategoryRes.getPlaceName())
                .dong(null)
                .address(searchSpotCategoryRes.getAddressName())
                .spotPoint(new Point(((Double)searchSpotCategoryRes.getX()).intValue(),((Double)searchSpotCategoryRes.getY()).intValue()))
                .x(searchSpotCategoryRes.getX())
                .y(searchSpotCategoryRes.getY())
                .build());



//        return SpotDto.builder().categoryCode(spot.getCategoryCode())
//                .categoryName(searchSpotCategoryRes.getCategoryGroupName())
//                .spotName(searchSpotCategoryRes.getCategoryName())
//                .dong(tempdong)
//                .address(searchSpotCategoryRes.getAddressName())
//                .spotPoint(new Point(((Double)searchSpotCategoryRes.getX()).intValue(),((Double)searchSpotCategoryRes.getY()).intValue()))
//                .x(searchSpotCategoryRes.getX())
//                .y(searchSpotCategoryRes.getY()).build();
    }

    @Override
    public List<SearchSpotCategoryRes> getSpotList() {
        return null;
    }

    @Override
    public Spot getSpot(Long spotId) {
        //Optional<Spot> res=spotRepository.findById(1L);

        return null;
    }

    @Override
    public SearchSpotCategoryRes updateSpot(SearchSpotCategoryRes searchSpotCategoryRes) {
        return null;
    }


    @Override
    public void deleteSpot(Long spotId) {

    }
}
