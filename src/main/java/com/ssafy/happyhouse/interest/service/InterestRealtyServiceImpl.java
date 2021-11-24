package com.ssafy.happyhouse.interest.service;

import com.ssafy.happyhouse.interest.entity.InterestRealty;
import com.ssafy.happyhouse.interest.model.InterestRealtyDto;
import com.ssafy.happyhouse.interest.repository.InterestRealtyRepository;
import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.realty.repository.RealtyRepository;
import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestRealtyServiceImpl implements InterestRealtyService{

    private final UserRepository userRepository;
    private final RealtyRepository realtyRepository;
    private final InterestRealtyRepository interestRealtyRepository;

    @Override
    public List<Realty> getInterestRealtyList(String username, Pageable pageable) throws Exception{
        User user = userRepository.findByUsername(username).get();
        List<Realty> list = interestRealtyRepository.findAllByUser(user, pageable);
        return list;
    }

    @Override // 매퍼로 매핑해서 다시 리턴하는것인가?
    public InterestRealtyDto saveInterestRealty(String username, Long realtyId) throws Exception{
        User user = userRepository.findByUsername(username).get();
        Realty realty = realtyRepository.findById(realtyId).get();
        interestRealtyRepository.save(InterestRealty.builder().realty(realty).user(user).build());
        return new InterestRealtyDto(realty, user);
    }

    @Override
    public void deleteInterestRealty(String username, Long realtyId) throws Exception{
        User user = userRepository.findByUsername(username).get();
        Realty realty = realtyRepository.findById(realtyId).get();
        InterestRealty interestRealty = interestRealtyRepository.findByUserAndRealty(user, realty);
        interestRealtyRepository.delete(interestRealty);
    }

}
