package com.ssafy.happyhouse.realty.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.realty.entity.RealtyPicture;
import com.ssafy.happyhouse.realty.model.Marker;
import com.ssafy.happyhouse.realty.model.RealtyDto;
import com.ssafy.happyhouse.realty.model.RealtyPicturesDto;
import com.ssafy.happyhouse.realty.model.RealtyResponseDto;
import com.ssafy.happyhouse.realty.service.RealtyService;
import com.ssafy.happyhouse.realty.utils.MD5Generator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/realty")
@RequiredArgsConstructor
@Slf4j
public class realtycontroller {
    private final RealtyService realtyService;

    // RequestParam으로 문자열 받을 것, PathVariable은 숫자 등, get할때만사용 => body가 없으므로
    //등록 , dong_id와 realty_point
    @PostMapping
    public ResponseEntity<String> createRealty(@RequestBody RealtyDto realtyDto //매물 등록 내용
                                               , @RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken
                                               ) throws IOException, NoSuchAlgorithmException {

        //토큰에서 등록자 name뽑기
        String token = bearerToken.replace("Bearer ","");//기본적으로 header에 Bearer를 먼저 넣어주고 한다.
        DecodedJWT decodedJWT = JWT.decode(token);//디코딩
        String username = decodedJWT.getSubject();//이름 뽑아오기
        try{
            realtyService.saveRealty(realtyDto,username);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //수정, 매물id와 realtyDto, id로 realty(이미 등록된 애) -> 바뀐걸 체크해주고 -> save()
    @PutMapping("/{realty-id}")
    public ResponseEntity<RealtyResponseDto> updateRealty(@PathVariable(name="realty-id") Long realtyId,@RequestBody RealtyDto realtyDto){
        realtyDto.setRealtyId(realtyId);//어떤 애인지 아이디를 Dto에 박아버림
        RealtyResponseDto realtyResponseDto = null;
        try{
            realtyResponseDto = realtyService.updateRealty(realtyDto);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(realtyResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> uploadImage(@ModelAttribute List<MultipartFile> files){
        try{
            realtyService.saveImage(files);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //삭제
    @DeleteMapping("/{realty-id}")
    public ResponseEntity<Void> deleteRealty(@PathVariable(name="realty-id") Long realtyId){
        realtyService.deleteRealty(realtyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Marker>> getRealtyMarkers(){
        return new ResponseEntity<>(realtyService.getRealtyMarkers(), HttpStatus.OK);
    }

    //

    //키워드 기반 조회(마커)

    //

    //해당 지역 추천 매물 뿌려주기(조회순 및 라이크 순)
    //토큰이있다면 username으로 user찾아서 interestdistrict 1번 찾아서 dong으로 realty 찾아서 보내주던데
    @GetMapping(path = "/recommend")
    
    public ResponseEntity<List<Marker>> getRecommendedRealty(@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken){
        //토큰에서 등록자 name뽑기
        String token = bearerToken.replace("Bearer ","");//기본적으로 header에 Bearer를 먼저 넣어주고 한다.
        DecodedJWT decodedJWT = JWT.decode(token);//디코딩
        String username = decodedJWT.getSubject();//이름 뽑아오기
        List<Marker> recommendedMarker;
        //사용자가 관심지역이 없다면? => Exception
        try{
            recommendedMarker = realtyService.getRecommendedMarkers(username);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(recommendedMarker, HttpStatus.OK);
    }
    //상세 조회
    @GetMapping(path = "/{realty-id}")
    public ResponseEntity<RealtyResponseDto> getRealty(@PathVariable(name = "realty-id") Long realtyId){
        return new ResponseEntity<>(realtyService.getRealty(realtyId),HttpStatus.OK);
    }

}
