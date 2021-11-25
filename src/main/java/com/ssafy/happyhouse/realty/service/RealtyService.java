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
    public Dong checkValidDong(String dongstr,List<String> dongValues);
    public RealtyResponseDto updateRealty(RealtyDto realtyDto) throws Exception;
    public String saveImage(List<MultipartFile> multipartFile, Long realtyId) throws Exception;
    public void updateImage(List<MultipartFile> multipartFile, Long realtyId) throws Exception;
    public String saveRealty(RealtyDto realtyDto,String username) throws Exception;
    public void deleteRealty(Long realtyId);
    public List<Marker> getRealtyMarkers();
    public RealtyResponseDto getRealty(Long realtyId);
    public List<Marker> getRecommendedMarkers(String username);
}
