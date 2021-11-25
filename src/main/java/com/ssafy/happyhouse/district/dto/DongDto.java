package com.ssafy.happyhouse.district.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DongDto {
    private Long dongId;

    private String dongCode;

    private String sidoName;

    private String gugunName;

    private String dongName;

}
