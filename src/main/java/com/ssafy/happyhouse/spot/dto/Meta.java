package com.ssafy.happyhouse.spot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private boolean isEnd=false;
    private int pageableCount;
    private String sameName;
    private int totalCount;
}
