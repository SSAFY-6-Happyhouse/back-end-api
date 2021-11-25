package com.ssafy.happyhouse.user.model;

import com.ssafy.happyhouse.district.dto.DongDto;
import com.ssafy.happyhouse.interest.entity.InterestDistrict;
import com.ssafy.happyhouse.interest.model.InterestDistrictDto;
import com.ssafy.happyhouse.spot.entity.Segwon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDto {

    private Long userId;

    private String username;

    private String name;

    private String phone;

    private List<DongDto> interestDistricts;

    private List<Segwon> segwons = new ArrayList<>();

}
