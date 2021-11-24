package com.ssafy.happyhouse.realty.service;

import com.ssafy.happyhouse.realty.entity.RealtyPicture;
import com.ssafy.happyhouse.realty.model.Marker;
import com.ssafy.happyhouse.realty.model.RealtyDto;
import com.ssafy.happyhouse.realty.model.RealtyPicturesDto;
import org.springframework.web.multipart.MultipartFile;
import com.ssafy.happyhouse.realty.model.RealtyResponseDto;

import java.util.List;

public interface RealtyService {
    public String saveImage(List<MultipartFile> multipartFile) throws Exception;
    public String saveRealty(RealtyDto realtyDto,String username);
    public RealtyDto updatePost(RealtyDto realtyDto);
    public void deleteRealty(Long realtyId);
    public List<Marker> getRealtyMarkers();
    public RealtyResponseDto getRealty(Long realtyId);
    public List<Marker> getRecommendedMarkers(String username);
    public List<RealtyDto> getRealtyList();
}
