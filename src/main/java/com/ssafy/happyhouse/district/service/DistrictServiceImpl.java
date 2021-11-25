package com.ssafy.happyhouse.district.service;

import com.ssafy.happyhouse.district.dto.DongDto;
import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.district.entity.Gugun;
import com.ssafy.happyhouse.district.entity.Sido;
import com.ssafy.happyhouse.district.repository.DongRepository;
import com.ssafy.happyhouse.district.repository.GugunRepository;
import com.ssafy.happyhouse.district.repository.SidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService{
    private final DongRepository dongRepository;
    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    public List<Sido> getAllSido(){
        return sidoRepository.findAll();
    }

    public List<Gugun> getAllGugunFromSido(String gugunCode){
        return gugunRepository.findAllByGugunCodeStartingWith(gugunCode.substring(0,2));
    }
    public List<DongDto> getAllDongFromGugun(String dongCode){
        return dongRepository.findAllByDongCodeStartingWith(dongCode.substring(0,5))
                .stream().map(dong -> DongDto.builder().dongId(dong.getDongId())
                        .dongCode(dong.getDongCode())
                        .dongName(dong.getDongName())
                        .gugunName(dong.getGugunName())
                        .build())
                .collect(Collectors.toList());
    }


}
