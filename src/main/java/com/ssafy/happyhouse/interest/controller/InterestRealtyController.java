package com.ssafy.happyhouse.interest.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.happyhouse.interest.model.InterestRealtyDto;
import com.ssafy.happyhouse.interest.service.InterestRealtyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interest")
@RequiredArgsConstructor
@Slf4j
public class InterestRealtyController {

    private final InterestRealtyService interestRealtyService;

    @GetMapping
    public ResponseEntity<List<InterestRealtyDto>> getInterestRealty(@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken){
        String token = bearerToken.replace("Bearer ","");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        List<InterestRealtyDto> list = interestRealtyService.getInterestRealtyList(username);
        return new ResponseEntity<List<InterestRealtyDto>>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InterestRealtyDto> createInterestRealty(@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken
                                                                , @RequestParam(name = "realty-id") Long realtyId){
        String token = bearerToken.replace("Bearer ","");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        InterestRealtyDto interestRealtyDto = interestRealtyService.saveInterestRealty(username, realtyId);
        return new ResponseEntity<>(interestRealtyDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteInterestRealty(@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken
                                                    , @RequestParam(name = "realty-id") Long realtyId){
        String token = bearerToken.replace("Bearer ","");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        interestRealtyService.deleteInterestRealty(username, realtyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
