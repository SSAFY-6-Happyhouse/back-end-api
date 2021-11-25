package com.ssafy.happyhouse.user.model;

import com.ssafy.happyhouse.interest.entity.InterestDistrict;
import com.ssafy.happyhouse.spot.entity.Segwon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {
    private String username;
    private String password;
    private String phone;
    private String interestDistricts;
    private List<Segwon> segwons; // 수정 필요
}
