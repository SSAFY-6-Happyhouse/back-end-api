package com.ssafy.happyhouse.enquiry.entity.service;

import com.ssafy.happyhouse.enquiry.entity.model.EnquiryDto;

public interface EnquiryService {
    public EnquiryDto getEnquiryByRealtyId(Long realtyId);
    public EnquiryDto saveEnquiry(String username, Long realtyId);
}
