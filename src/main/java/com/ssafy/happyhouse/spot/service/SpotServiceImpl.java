package com.ssafy.happyhouse.spot.service;

import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SpotDto;
import com.ssafy.happyhouse.spot.entity.Direction;
import com.ssafy.happyhouse.spot.entity.Segwon;
import com.ssafy.happyhouse.spot.entity.Spot;
import com.ssafy.happyhouse.spot.entity.SpotKeyword;
import com.ssafy.happyhouse.spot.repository.SpotRepository;
import com.ssafy.happyhouse.spot.utils.GeometryUtil;
import com.ssafy.happyhouse.spot.utils.Location;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.*;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SpotServiceImpl implements SpotService{

    private final SpotRepository spotRepository;

    private final EntityManager entityManager;

    //private final DongRepository dongRepository;

    //private final ModelMapper modelMapper;

    @Override
    public void saveSpot(SearchSpotCategoryRes searchSpotCategoryRes) throws ParseException {
        //동구하기
        String[] str=searchSpotCategoryRes.getAddressName().split(" ");
        //Dong dong = dongRepository.findByDongName(str[2]);


        String pointWKT = String.format("POINT(%s %s)",searchSpotCategoryRes.getX(),searchSpotCategoryRes.getY());
        Point point=(Point) new WKTReader().read(pointWKT);

        spotRepository.save(Spot.builder()
                        .categoryCode(searchSpotCategoryRes.getCategoryGroupCode())
                .categoryName(searchSpotCategoryRes.getCategoryGroupName())
                .spotName(searchSpotCategoryRes.getPlaceName())
                .dong(null)
                .address(searchSpotCategoryRes.getAddressName())
                .spotPoint(point)
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
                            //.spotPoint(category.getSpotPoint())
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
                //.spotPoint(res.getSpotPoint())
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

    @Override
    public List<Segwon> getSegwonList(org.springframework.data.geo.Point point) throws ParseException {
        double baseLatitude= point.getY();
        double baseLongitude = point.getX();
        double distance = 0.5;

        // 북동쪽 좌표 구하기
        Location northEast = GeometryUtil.calculate(baseLatitude, baseLongitude, distance, Direction.NORTHEAST.getBearing());

        // 남서쪽 좌표 구하기
        Location southWest = GeometryUtil.calculate(baseLatitude, baseLongitude, distance, Direction.SOUTHWEST.getBearing());

        double x1 = northEast.getLongitude();
        double y1 = northEast.getLatitude();
        double x2 = southWest.getLongitude();
        double y2 = southWest.getLatitude();

        // native query 활용
        Query query = entityManager.createNativeQuery("" +
                "SELECT s.spot_category_code \n" +
                "FROM spots AS s \n" +
                "WHERE MBRContains(ST_LINESTRINGFROMTEXT(" + String.format("'LINESTRING(%f %f, %f %f)')", x1, y1, x2, y2) + ", s.spot_point)"
        );


        List<String> result = query.getResultList();

        Set<Segwon> segwons =new HashSet<>();
        for(String value : result){
            for (Segwon segwon:Segwon.values()){
                if(value.equals(segwon.getCode())){
                    segwons.add(segwon);
                }
            }
        }

        List<Segwon> segwonList=new ArrayList<>(segwons);

        return segwonList;
    }
}
