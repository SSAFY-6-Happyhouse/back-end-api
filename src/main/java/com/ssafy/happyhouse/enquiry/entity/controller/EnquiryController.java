package com.ssafy.happyhouse.enquiry.entity.controller;

import com.ssafy.happyhouse.enquiry.entity.model.EnquiryDto;
import com.ssafy.happyhouse.enquiry.entity.service.EnquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/realty/{realty-id}")
    public ResponseEntity<EnquiryDto> getEnquiry(@RequestParam(name = "realty-id") Long realtyId){
        EnquiryDto enquiryDto = enquiryService.getEnquiryByRealtyId(realtyId);
        if(ObjectUtils.isEmpty(enquiryDto)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(enquiryDto, HttpStatus.OK);
    }

    @PostMapping("/realty/{realty-id}")
    public ResponseEntity<EnquiryDto> saveEnquiry(@RequestParam(name = "realty-id")Long realtyId, @RequestParam(name = "username")String username){
        EnquiryDto enquiryDto = enquiryService.saveEnquiry(username, realtyId);
        return new ResponseEntity<>(enquiryDto, HttpStatus.OK);
    }

}
