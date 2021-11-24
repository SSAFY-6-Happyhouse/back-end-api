package com.ssafy.happyhouse.interest.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.happyhouse.interest.model.InterestDistrictDto;
import com.ssafy.happyhouse.interest.service.InterestDistrictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interestdistict")
@RequiredArgsConstructor
@Slf4j
public class InterestDistrictController {

    private final InterestDistrictService interestDistrictService;

    public List<InterestDistrictDto> getInterestDistrict(@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken){
        String token = bearerToken.replace("Bearer ","");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        return null;
    }
}
