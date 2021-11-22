package com.ssafy.happyhouse.realty.model;

import com.ssafy.happyhouse.realty.entity.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class realty_optionsDto {
    private RealtyDto realtyDto;
    private List<Option> options;
}
