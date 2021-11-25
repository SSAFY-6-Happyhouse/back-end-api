package com.ssafy.happyhouse.util.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchAddressReq {
    private String dongAddress;
    private String specificAddress;

    public MultiValueMap<String,String> toMultiValueMap(){ //query parameter를 넣어서 미리 형식을 맞춰두기 위한 메소드이다.
        MultiValueMap<String,String> map=new LinkedMultiValueMap();

        map.add("query",dongAddress+ " " + specificAddress);

        return map;
    }
}
