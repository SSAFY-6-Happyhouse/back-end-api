package com.ssafy.happyhouse.spot.controller;

import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryReq;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryResMain;
import com.ssafy.happyhouse.spot.dto.SpotDto;
import com.ssafy.happyhouse.spot.entity.Segwon;
import com.ssafy.happyhouse.spot.entity.Spot;
import com.ssafy.happyhouse.spot.entity.SpotKeyword;
import com.ssafy.happyhouse.spot.service.SpotService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.io.ParseException;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Scheduled(cron = "0 0 12 ? * WED") // 매월 아무날짜의 매주 수요일 12:00:00 에 스케줄링
    @GetMapping("/c")
    public void updateSpot() throws Exception{
        deleteSpot();
        registerSpot();
    }

    //@GetMapping("/b")
    private void deleteSpot() throws Exception {
      spotService.deleteSpot();
    }

    //@GetMapping("/a")
    public void registerSpot() throws Exception {

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

    @GetMapping("/test")
    public List<Segwon> test() throws Exception {
        //List<Segwon> list=spotService.getSegwonList(new Point(127.05333666716001,37.54002651744539)); //마세권 별세권 리턴
        List<Segwon> list=spotService.getSegwonList(126.981341046733,37.5693096043926); //별세권, 주세권, 편세권 리턴
        return list;
    }

    @PostMapping
    public ResponseEntity<List<SpotDto>> getSegwonList(@RequestParam(value = "segwons") List<Long> segwons) throws Exception {
        List<SpotKeyword> spotkeyword = new ArrayList<>();
        //realty 에서 segwons정보 등록할때 호출시 무슨 세권인지 준다.
        for(int i=0;i<segwons.size();i++){
            spotkeyword.add(SpotKeyword.values()[segwons.get(i).intValue()]);
        }
        List<SpotDto> spotlist= spotService.getSpotList(spotkeyword);
        return new ResponseEntity<>(spotlist,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<SpotDto> getSpot(@RequestParam("spot_id") Long spotId) throws Exception {
        SpotDto spotDto=spotService.getSpot(spotId);
        return new ResponseEntity<>(spotDto,HttpStatus.OK);
    }



//    @GetMapping() //주소와 세권을 주면 있으면 list와 Ok 없으면 null을 보내준다.
//    public ResponseEntity<List<SpotDto>> getSpotList(@RequestParam("segwon_id") Long segwonId) throws Exception{
//        SpotKeyword spotKeyword=SpotKeyword.values()[segwonId.intValue()];
//    }
}
