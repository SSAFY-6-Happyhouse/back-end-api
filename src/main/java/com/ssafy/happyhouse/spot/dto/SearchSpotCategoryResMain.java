package com.ssafy.happyhouse.spot.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchSpotCategoryResMain {
    private List<SearchSpotCategoryRes> documents=new ArrayList<>();
    private Meta meta;
}
