package com.ssafy.happyhouse.spot.controller;

import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryReq;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryResMain;
import com.ssafy.happyhouse.spot.service.SpotService;
import com.ssafy.happyhouse.spot.service.SpotServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
spot 정보를 얻어오는 controller이다.
kakao API 연동 / 검색 키워드로 spot정보 얻어오기 / DB 저장
* */


@Data
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/segwon")
public class SpotController {
    private SpotService spotService;

    private final SpotGetAPIController controller;

    @GetMapping("/a")
    public SearchSpotCategoryResMain check(){
        SearchSpotCategoryReq temp22=new SearchSpotCategoryReq();
        temp22.setCategoryGroupCode("MT1");

        SearchSpotCategoryResMain temp=controller.searchSpot(temp22);
        SearchSpotCategoryRes res=temp.getDocuments().get(0);
        //System.out.println(res.getCategoryGroupCode());
        return temp;
    }
}
