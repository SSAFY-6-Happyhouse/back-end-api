package com.ssafy.happyhouse.spot.controller;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.spot.dto.Meta;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryReq;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryResMain;
import com.ssafy.happyhouse.spot.entity.SpotKeyword;
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

import java.util.List;

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
    private final SpotService spotService;

    private final SpotGetAPIController controller;

    @GetMapping("/a")
    public void registerSpot(){

        //임시
        Long dongtemp = 1L;

        for(SpotKeyword spotKeyword : SpotKeyword.values()) {
            SearchSpotCategoryReq spotreq = new SearchSpotCategoryReq();
            spotreq.setCategoryGroupCode(spotKeyword.toString());


            //15개씩밖에 안가져오니까 이걸로 전체 페이지 가져오겠다.
            while(spotreq.getPage()<46){
                SearchSpotCategoryResMain spotres = controller.searchSpot(spotreq);
                List<SearchSpotCategoryRes> res = spotres.getDocuments();
                spotreq.setPage(spotreq.getPage()+1);


                for(SearchSpotCategoryRes temp : res){

                    spotService.saveSpot(temp);
                }
            }
        }
    }



}
