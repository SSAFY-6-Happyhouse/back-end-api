package com.ssafy.happyhouse.realty.service;

import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.realty.model.RealtyDto;
import com.ssafy.happyhouse.realty.repository.RealtyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealtyServiceImpl implements RealtyService{
    private final RealtyRepository realtyRepository;
//    private final ModelMapper modelMapper;

    @Override
    public String saveRealty(RealtyDto realtyDto) {
        try{
            realtyRepository.save(realtyDto.toEntity());
        }catch (Exception e){
            throw e;
        }
        return "sucess";
    }

    @Override
    public RealtyDto updatePost(RealtyDto realtyDto) {
        Realty realty = realtyRepository.findById(realtyDto.getRealtyId()).get();
        realty =realty.updateRealty(realtyDto.toEntity());
        return null;
    }

    @Override
    public void deleteRealty(Long realtyId) {
        realtyRepository.deleteById(realtyId);
    }
}
