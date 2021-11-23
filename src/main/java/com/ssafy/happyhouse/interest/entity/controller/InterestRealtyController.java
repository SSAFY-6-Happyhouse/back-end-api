package com.ssafy.happyhouse.interest.entity.controller;

import com.ssafy.happyhouse.interest.entity.model.InterestRealtyDto;
import com.ssafy.happyhouse.interest.entity.service.InterestRealtyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<List<InterestRealtyDto>> getInterestRealty(@RequestParam(name = "user_id") String userId){
        return null;
    }

    @PostMapping
    public ResponseEntity<InterestRealtyDto> saveInterestRealty(){
        return null;
    }

    @PutMapping
    public ResponseEntity<InterestRealtyDto> updateInterestRealty(){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<InterestRealtyDto> deleteInterestRealty(){
        return null;
    }
}
