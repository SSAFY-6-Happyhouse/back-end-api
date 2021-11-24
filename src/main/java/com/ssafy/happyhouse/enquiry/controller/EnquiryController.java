package com.ssafy.happyhouse.enquiry.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.happyhouse.enquiry.model.EnquiryDto;
import com.ssafy.happyhouse.enquiry.service.EnquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enquiry")
@RequiredArgsConstructor
@Slf4j
public class EnquiryController {
    private final EnquiryService enquiryService;

    @GetMapping("/{realty-id}")
    public ResponseEntity<EnquiryDto> getEnquiry(@PathVariable(name = "realty-id") Long realtyId
                                                , @RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken){
        String token = bearerToken.replace("Bearer ","");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        EnquiryDto enquiryDto;
        try{
           enquiryDto = enquiryService.getEnquiryByRealtyId(realtyId, username);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(ObjectUtils.isEmpty(enquiryDto)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(enquiryDto, HttpStatus.OK);
    }

    @PostMapping("/{realty-id}")
    public ResponseEntity<EnquiryDto> saveEnquiry(@PathVariable(name = "realty-id")Long realtyId
                                                , @RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken){
        String token = bearerToken.replace("Bearer ","");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getSubject();
        EnquiryDto enquiryDto;
        try{
            enquiryDto = enquiryService.saveEnquiry(username, realtyId);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(enquiryDto, HttpStatus.OK);
    }

}
