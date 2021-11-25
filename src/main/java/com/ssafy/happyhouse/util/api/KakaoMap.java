package com.ssafy.happyhouse.util.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.happyhouse.realty.model.Coordinate;
import com.ssafy.happyhouse.spot.dto.SearchSpotCategoryResMain;
import com.ssafy.happyhouse.spot.utils.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@Slf4j
@RequestMapping(path = "/kakaomap")
public class KakaoMap {
    @Value("${kakao.url.address}")
    private String kakaoAddressSearchUrl;

    @Value("${kakao.key.Authorization}")
    private String kakaoAuthrization;

    //주소값을 바탕으로 좌표 저장하는 작업
    @GetMapping
    public Coordinate getCoordinatesFromAddress(){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",kakaoAuthrization);
        String dong = "서울특별시 양천구";
        String address = "목동서로100";
        URI uri = UriComponentsBuilder.fromUriString(kakaoAddressSearchUrl)
                .queryParams(SearchAddressReq.builder().dongAddress(dong).specificAddress(address).build().toMultiValueMap())
                .build()
                .encode()
                .toUri();

        HttpEntity httpEntity = new HttpEntity(headers);
        ParameterizedTypeReference responseType=new ParameterizedTypeReference<SearchAddressResMain>(){};

        ResponseEntity<Object> responseEntity =
                new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, responseType);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            SearchAddressResMain searchAddressResMain = (SearchAddressResMain) responseEntity.getBody();
            log.info(responseEntity.getBody().toString());

            log.info(searchAddressResMain.getDocument().get(0).getY().toString());
            log.info(searchAddressResMain.getDocument().get(0).getX().toString());
            return Coordinate.builder()
                    .latitude(searchAddressResMain.getDocument().get(0).getY())
                    .longitude(searchAddressResMain.getDocument().get(0).getX())
                    .build();
        }
        return null;
    }
}
