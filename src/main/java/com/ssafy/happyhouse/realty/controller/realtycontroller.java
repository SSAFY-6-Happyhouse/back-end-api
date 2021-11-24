package com.ssafy.happyhouse.realty.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.realty.model.RealtyDto;
import com.ssafy.happyhouse.realty.service.RealtyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/realty")
@RequiredArgsConstructor
@Slf4j
public class realtycontroller {
    private final RealtyService realtyService;
    // RequestParam으로 문자열 받을 것, PathVariable은 숫자 등, get할때만사용 => body가 없으므로
    //등록
    @PostMapping
    public ResponseEntity<String> createRealty(@RequestBody RealtyDto realtyDto //매물 등록 내용
                                               ,@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken
    ){//registerer확인용
        //토큰에서 등록자 name뽑기
        String token = bearerToken.replace("Bearer ","");//기본적으로 header에 Bearer를 먼저 넣어주고 한다.
        DecodedJWT decodedJWT = JWT.decode(token);//디코딩
        String username = decodedJWT.getSubject();//이름 뽑아오기
//        //동네 이름, StringTokenizer로

        //
//        String username ="";
        log.info(realtyDto.toString());
        realtyService.saveRealty(realtyDto,username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //수정
//    @PutMapping("/{realty-id}")
//    public ResponseEntity<RealtyDto> updateRealty(@PathVariable(name="realty-id") Long realtyId,@RequestBody RealtyDto realtyDto){
//        RealtyDto realtyDto1;
//        realtyDto.setRealtyId(realtyId);
//        realtyDto1 = realtyService.updateRealty(realtyDto);
//        return new ResponseEntity<>(realtyDto1, HttpMethod.OK);
//    }

    //삭제
    @DeleteMapping("/{realty-id}")
    public ResponseEntity<Void> deleteRealty(@PathVariable(name="realty-id") Long realtyId){
        realtyService.deleteRealty(realtyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //키워드 기반 전체 조회,넘어오올땐 requestParam으로 넘어서 옴. map좌표 포함해서 받을 듯
    @GetMapping
    public ResponseEntity<List<RealtyDto>> getRealtyList(){
        return new ResponseEntity<>(realtyService.getRealtyList(), HttpStatus.OK);
    }

    //추천 매물 뿌려주기(조회순 및 라이크 순)


    //상세 조회
    @GetMapping(path = "/{realty-id}")
    public ResponseEntity<RealtyDto> getRealty(@PathVariable(name = "realty-id") Long realtyId){
        return new ResponseEntity<>(realtyService.getRealty(realtyId),HttpStatus.OK);
    }

    //추천 검색어??
}
