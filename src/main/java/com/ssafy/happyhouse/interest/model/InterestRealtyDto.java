package com.ssafy.happyhouse.interest.model;

import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterestRealtyDto {

    private Realty realty;

    private User user;
}
