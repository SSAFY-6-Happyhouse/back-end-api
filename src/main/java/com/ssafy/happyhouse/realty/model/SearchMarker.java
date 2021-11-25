package com.ssafy.happyhouse.realty.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchMarker {

    private Long dongCode;
    private List<Long> contractType;
    private List<Long> realtyType;
    private Long price;
    private List<Long> segwons;
    private LocalDateTime localDateTime;
    private String keyword;

}
