package com.ssafy.happyhouse.enquiry.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnquiryDto {
    private Long enquiryId;
    private String realtyProviderPhone;
}
