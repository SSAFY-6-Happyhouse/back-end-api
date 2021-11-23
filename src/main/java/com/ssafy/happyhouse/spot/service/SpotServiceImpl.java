package com.ssafy.happyhouse.spot.service;

import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SpotDto;
import com.ssafy.happyhouse.spot.entity.Spot;
import com.ssafy.happyhouse.spot.entity.SpotKeyword;
import com.ssafy.happyhouse.spot.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SpotServiceImpl implements SpotService{

    private final SpotRepository spotRepository;
    //private final DongRepository dongRepository;

    //private final ModelMapper modelMapper;

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
    public List<SpotDto> getSpotList(List<SpotKeyword> segwons) {
        List<SpotDto> list=new ArrayList<>();
//        segwons=new ArrayList<>();
//        segwons.add(SpotKeyword.AT4);
//        segwons.add(SpotKeyword)
        for(SpotKeyword spot : segwons) {
            Spot category=spotRepository.findByCategoryCode(spot);
            //list.add(modelMapper.map(category,SpotDto.class));
            list.add(SpotDto.builder().
                    categoryName(category.getCategoryName())
                            .categoryCode(category.getCategoryCode())
                            .spotName(category.getSpotName())
                            .address(category.getAddress())
                            .x(category.getX())
                            .y(category.getY())
                            .spotPoint(category.getSpotPoint())
                            .dong(category.getDong())
                    .build());
        }
        return list;
    }

    @Override
    public SpotDto getSpot(Long spotId) {
        Spot res=spotRepository.findById(spotId).get(); //여기서 에러 ( Spot 에 constructor 안해서 에러)

        return SpotDto.builder()
                .categoryCode(res.getCategoryCode())
                .categoryName(res.getCategoryName())
                .address(res.getAddress())
                .spotName(res.getSpotName())
                .x(res.getX())
                .y(res.getY())
                .spotPoint(res.getSpotPoint())
                .dong(res.getDong())
                .build();
    }

    @Override
    public SpotDto updateSpot(SpotDto spotDto) {
        return null;
    }


    @Override
    public void deleteSpot(Long spotId) {

    }
}
