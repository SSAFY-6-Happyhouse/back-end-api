package com.ssafy.happyhouse.enquiry.service;

import com.ssafy.happyhouse.enquiry.model.EnquiryDto;

public interface EnquiryService {
    public EnquiryDto getEnquiryByRealtyId(Long realtyId) throws Exception;
    public EnquiryDto saveEnquiry(String username, Long realtyId) throws Exception;
}
