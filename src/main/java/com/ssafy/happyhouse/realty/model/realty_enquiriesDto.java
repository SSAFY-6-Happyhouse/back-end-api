package com.ssafy.happyhouse.realty.model;

import com.ssafy.happyhouse.enquiry.entity.Enquiry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class realty_enquiriesDto {
    private RealtyDto realtyDto;
    private List<Enquiry> enquiries;
}
