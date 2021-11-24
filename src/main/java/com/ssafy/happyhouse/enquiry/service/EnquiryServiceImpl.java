package com.ssafy.happyhouse.enquiry.service;

import com.ssafy.happyhouse.enquiry.entity.Enquiry;
import com.ssafy.happyhouse.enquiry.model.EnquiryDto;
import com.ssafy.happyhouse.enquiry.repository.EnquiryRepository;
import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.realty.repository.RealtyRepository;
import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class EnquiryServiceImpl implements EnquiryService {

    private final RealtyRepository realtyRepository;
    private final EnquiryRepository enquiryRepository;
    private final UserRepository userRepository;

    @Override
    public EnquiryDto getEnquiryByRealtyId(Long realtyId) throws Exception{
        Realty realty = realtyRepository.findById(realtyId).get();
        Enquiry enquiry = enquiryRepository.findByRealty(realty).get();

        if(ObjectUtils.isEmpty(enquiry)){
            return null;
        }
        return EnquiryDto.builder().enquiryId(enquiry.getEnquiryId())
                                    .realtyProviderPhone(enquiry.getRealtyProviderPhone())
                                    .build();
    }

    @Override
    public EnquiryDto saveEnquiry(String username, Long realtyId) throws Exception{
        // 유저, 매물정보, 전화번호 (명령형식의 코드 작성은 메모리 효율성이 떨어질 수 있다)
        User user = userRepository.findByUsername(username).get();
        Realty realty = realtyRepository.findById(realtyId).get();
        Enquiry enquiry = enquiryRepository.save(Enquiry.builder()
                                                        .user(user)
                                                        .realty(realty)
                                                        .realtyProviderPhone(realty.getRegisterer().getPhone()).build());

        return EnquiryDto.builder().enquiryId(enquiry.getEnquiryId())
                .realtyProviderPhone(enquiry.getRealtyProviderPhone())
                .build();

    }
}
