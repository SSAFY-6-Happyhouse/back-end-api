package com.ssafy.happyhouse.spot.controller;

/*
spot 정보를 얻어오는 controller이다.
kakao API 연동 / 검색 키워드로 spot정보 얻어오기 / DB 저장
* */

import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryReq;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryRes;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryResMain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Controller
@CrossOrigin("/*") //host : dapi.kakak.com, GET : /v2/local/search/keyword.{FORMAT - 기본 json} http/1.1 방식 , Authorization : kakakoAK {REST_API_KEY}
public class SpotGetAPIController {

    //지하철역 예제
    @Value("${kakao.url.category}")
    private  String kakaoCategorySearchUrl;

    @Value("${kakao.key.Authorization}")
    private String kakaoAuthrization;

    public SearchSpotCategoryResMain searchSpot(SearchSpotCategoryReq searchSpotCategoryReq){

        URI uri= UriComponentsBuilder.fromUriString(kakaoCategorySearchUrl)
                .queryParams(searchSpotCategoryReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",kakaoAuthrization);

        HttpEntity httpEntity=new HttpEntity(headers);
        ParameterizedTypeReference responseType=new ParameterizedTypeReference<SearchSpotCategoryResMain>(){};
        ResponseEntity<Object> responseEntity=new RestTemplate().exchange(
                uri, HttpMethod.GET,httpEntity,responseType
        );

        return (SearchSpotCategoryResMain) responseEntity.getBody();
    }

}
