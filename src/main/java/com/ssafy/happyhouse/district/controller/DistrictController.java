package com.ssafy.happyhouse.district.controller;

import com.ssafy.happyhouse.district.dto.DongDto;
import com.ssafy.happyhouse.district.entity.Gugun;
import com.ssafy.happyhouse.district.entity.Sido;
import com.ssafy.happyhouse.district.service.DistrictServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path  = "/district")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictServiceImpl districtService;

    @GetMapping(path = "/sido")
    public ResponseEntity<List<Sido>> getSido(){
        return new ResponseEntity<>(districtService.getAllSido(), HttpStatus.OK);
    }
    @GetMapping(path = "/gugun")
    public ResponseEntity<List<Gugun>> getGungun(@RequestParam(name = "sido") String sidoCode){
        return new ResponseEntity<>(districtService.getAllGugunFromSido(sidoCode),HttpStatus.OK);
    }
    @GetMapping(path = "/dong")
    public ResponseEntity<List<DongDto>> getDong(@RequestParam(name = "gugun") String gugunCode){
        return new ResponseEntity<>(districtService.getAllDongFromGugun(gugunCode), HttpStatus.OK);
    }
}
