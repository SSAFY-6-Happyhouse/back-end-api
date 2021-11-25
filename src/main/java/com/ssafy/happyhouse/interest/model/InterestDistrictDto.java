package com.ssafy.happyhouse.interest.model;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterestDistrictDto {
    private User user;
    private Dong dong;
}
