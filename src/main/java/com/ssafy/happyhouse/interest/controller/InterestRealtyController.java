package com.ssafy.happyhouse.interest.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.happyhouse.interest.model.InterestRealtyDto;
import com.ssafy.happyhouse.interest.service.InterestRealtyService;
import com.ssafy.happyhouse.realty.entity.Realty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interestrealty")
@RequiredArgsConstructor
@Slf4j
public class InterestRealtyController {

    private final InterestRealtyService interestRealtyService;

    @GetMapping
    public ResponseEntity<List<Realty>> getInterestRealty(@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken, Pageable pageable){
        String token = bearerToken.replace("Bearer ","");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        List<Realty> list;
        try{
            list = interestRealtyService.getInterestRealtyList(username, pageable);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InterestRealtyDto> createInterestRealty(@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken
                                                                , @RequestParam(name = "realty-id") Long realtyId){
        String token = bearerToken.replace("Bearer ","");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        InterestRealtyDto interestRealtyDto;
        try {
            interestRealtyDto = interestRealtyService.saveInterestRealty(username, realtyId);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(interestRealtyDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteInterestRealty(@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken
                                                    , @RequestParam(name = "realty-id") Long realtyId){
        String token = bearerToken.replace("Bearer ","");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        try {
            interestRealtyService.deleteInterestRealty(username, realtyId);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
