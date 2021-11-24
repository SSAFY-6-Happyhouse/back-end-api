package com.ssafy.happyhouse.interest.controller;

import com.ssafy.happyhouse.interest.service.InterestDistrictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interestdistict")
@RequiredArgsConstructor
@Slf4j
public class InterestDistrictController {
    private final InterestDistrictService interestDistrictService;
}
