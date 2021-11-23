package com.ssafy.happyhouse.spot.controller;

import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryReq;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryResMain;
import com.ssafy.happyhouse.spot.dto.SpotDto;
import com.ssafy.happyhouse.spot.entity.Spot;
import com.ssafy.happyhouse.spot.entity.SpotKeyword;
import com.ssafy.happyhouse.spot.service.SpotService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
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

        for(SpotKeyword spotKeyword : SpotKeyword.values()) {
            SearchSpotCategoryReq spotreq = new SearchSpotCategoryReq();
            spotreq.setCategoryGroupCode(spotKeyword.toString());

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

    @PostMapping
    public ResponseEntity<List<SpotDto>> getSpotList(@RequestParam(value = "segwons") List<Long> segwons){
        //임시로 테스트해볼라고
//        segwons=new ArrayList<>();
//        segwons.add(1L);
//        segwons.add(2L);
        List<SpotKeyword> spotkeyword = new ArrayList<>();
        //realty 에서 segwons정보 등록할때 호출시 무슨 세권인지 준다.
        for(int i=0;i<segwons.size();i++){
            spotkeyword.add(SpotKeyword.values()[segwons.get(i).intValue()]);
        }
        List<SpotDto> spotlist= spotService.getSpotList(spotkeyword);
        return new ResponseEntity<>(spotlist,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<SpotDto> getSpot(@RequestParam("spot_id") Long spotId){
        SpotDto spotDto=spotService.getSpot(spotId);
        return new ResponseEntity<>(spotDto,HttpStatus.OK);
    }


}
