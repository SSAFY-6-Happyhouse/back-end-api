package com.ssafy.happyhouse.spot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

////Spot 정보를 category별로 Response하기 위한 DTO
@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SearchSpotCategoryRes {
    private String id;                      //장소 ID
    //@JsonProperty("place_name")
    private String placeName;               //장소명, 업체명
    private String categoryName;            //카테고리 이름
    private String categoryGroupCode;       //중요카테고리만 그루핑한 카테고리 그룹코드
    private String categoryGroupName;       //중요 카테고리만 그룹핑한 카테고리 그룹명
    private String phone;                   //전화번호
    private String AddressName;             //전체 지번 주소
    private String roadAddressName;         //전체 도로명 주소
    private String x;                       //x좌표 혹은 경도(longitude)
    private String y;                       //y좌표 혹은 위도 (latitude)
    private String placeUrl;                //장소 상세페이지 URL
    private String distance;                //중심좌표까지의 거리 (단 x,y 파라미터를 준 경우에만 존재)
}
