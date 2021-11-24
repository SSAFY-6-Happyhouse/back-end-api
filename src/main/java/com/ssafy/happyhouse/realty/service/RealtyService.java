package com.ssafy.happyhouse.realty.service;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.realty.entity.RealtyPicture;
import com.ssafy.happyhouse.realty.model.Marker;
import com.ssafy.happyhouse.realty.model.RealtyDto;
import com.ssafy.happyhouse.realty.model.RealtyPicturesDto;
import org.springframework.web.multipart.MultipartFile;
import com.ssafy.happyhouse.realty.model.RealtyResponseDto;

import java.util.List;

public interface RealtyService {
    Dong checkValidDong(String dongstr,List<String> dongValues);
    String saveImage(List<MultipartFile> multipartFile) throws Exception;
    String saveRealty(RealtyDto realtyDto,String username) throws Exception;
    RealtyResponseDto updateRealty(RealtyDto realtyDto) throws Exception;
    void deleteRealty(Long realtyId);
    List<Marker> getRealtyMarkers();
    RealtyResponseDto getRealty(Long realtyId);
    List<RealtyResponseDto> getRealtyList();
}
