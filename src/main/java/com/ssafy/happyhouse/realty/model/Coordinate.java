package com.ssafy.happyhouse.realty.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
    private Double latitude;
    private Double longitude;
}
