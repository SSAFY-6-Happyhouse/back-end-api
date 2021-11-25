package com.ssafy.happyhouse.util.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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

@Component
@Slf4j
public class KakaoMap {
    @Value("${kakao.url.address}")
    private String kakaoAddressSearchUrl;

    @Value("${kakao.key.Authorization}")
    private String kakaoAuthrization;

    //주소값을 바탕으로 좌표 저장하는 작업
    public Coordinate getCoordinatesFromAddress(String dong, String address) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",kakaoAuthrization);
        URI uri = UriComponentsBuilder.fromUriString(kakaoAddressSearchUrl)
                .queryParams(SearchAddressReq.builder().dongAddress(dong).specificAddress(address).build().toMultiValueMap())
                .build()
                .encode()
                .toUri();

        HttpEntity httpEntity = new HttpEntity(headers);
        ParameterizedTypeReference responseType=new ParameterizedTypeReference<SearchAddressResMain>(){};

        ResponseEntity<String> responseEntity =
                new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, String.class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            String searchAddressResMain =  responseEntity.getBody();
            log.info(responseEntity.getBody().toString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
            Double longitude = jsonNode.get("documents").get(0).get("x").asDouble();
            Double latitude = jsonNode.get("documents").get(0).get("y").asDouble();

            return Coordinate.builder()
                    .longitude(longitude)
                    .latitude(latitude).build();


        }
        return null;
    }
}
