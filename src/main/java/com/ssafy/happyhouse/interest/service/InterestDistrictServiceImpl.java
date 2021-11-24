package com.ssafy.happyhouse.interest.service;

import com.ssafy.happyhouse.interest.entity.InterestDistrict;
import com.ssafy.happyhouse.interest.model.InterestDistrictDto;
import com.ssafy.happyhouse.interest.repository.InterestDistrictRepository;
import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestDistrictServiceImpl implements InterestDistrictService{

    private final UserRepository userRepository;
    private final InterestDistrictRepository interestDistrictRepository;

    @Override
    public InterestDistrictDto getInterestDistrictOne(String username) throws Exception{
        User user = userRepository.findByUsername(username).get();
        InterestDistrict interestDistrict = interestDistrictRepository.findFirstByUser(user);
        return new InterestDistrictDto(user, interestDistrict.getDong());
    }
}
