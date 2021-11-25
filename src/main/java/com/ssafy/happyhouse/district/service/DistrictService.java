package com.ssafy.happyhouse.district.service;

import com.ssafy.happyhouse.district.dto.DongDto;
import com.ssafy.happyhouse.district.entity.Gugun;
import com.ssafy.happyhouse.district.entity.Sido;

import java.util.List;

public interface DistrictService {
    public List<Sido> getAllSido();
    public List<Gugun> getAllGugunFromSido(String gugunCode);
    public List<DongDto> getAllDongFromGugun(String dongCode);

}
