package com.ssafy.happyhouse.spot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

//Spot 정보를 category별로 Reqeust하기 위한 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchSpotCategoryReq {
    private String categoryGroupCode;   //카테고리 코드
    private String x="";                   //중심좌표의 x값 혹은 longitude
    private String y="";                   //중심좌표의 y값 혹은 latitude
    private int radius=0;                 //중심좌표로부터의 반경거리 (동그라미 범위, 단위는 meter )
    private String rect="";                //사각형 범위내에서 제한 검색을 위한 좌표
    private int page=1;                   //결과 페이지 번호(기본값 1 / 1~45 사이의 값)
    private int size=15;                   //한페이지에 보여질 문서의 개수 (기본값 15 / 범위 1~15)
    private String sort="accuracy";                //결과 정렬 순서 (distance 정렬 혹은 accuracy 기본값은 accuracy)


    public MultiValueMap<String,String> toMultiValueMap(){ //query parameter를 넣어서 미리 형식을 맞춰두기 위한 메소드이다.
        MultiValueMap<String,String> map=new LinkedMultiValueMap();

        map.add("category_group_code",categoryGroupCode);
        map.add("x",x);
        map.add("y",y);
        map.add("redius",String.valueOf(radius));
        map.add("rect",rect);
        map.add("page",String.valueOf(page));
        map.add("size",String.valueOf(size));
        map.add("sort",sort);

        return map;
    }
}
