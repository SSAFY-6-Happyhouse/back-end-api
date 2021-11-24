package com.ssafy.happyhouse.realty.service;

import com.ssafy.happyhouse.realty.entity.RealtyPicture;
import com.ssafy.happyhouse.realty.model.Marker;
import com.ssafy.happyhouse.realty.model.RealtyDto;
import com.ssafy.happyhouse.realty.model.RealtyPicturesDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RealtyService {
    public String saveImage(List<MultipartFile> multipartFile) throws Exception;
    public String saveRealty(RealtyDto realtyDto,String username);
    public RealtyDto updatePost(RealtyDto realtyDto);
    public void deleteRealty(Long realtyId);
    public RealtyDto getRealty(Long realtyId);
    public List<Marker> getRealtyMarkers();
    public Long saveFile(RealtyPicturesDto realtyPictureDto);
    public RealtyPicturesDto getFile(Long id);
}
