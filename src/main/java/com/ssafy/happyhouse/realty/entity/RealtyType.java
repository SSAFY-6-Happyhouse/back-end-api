package com.ssafy.happyhouse.realty.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum RealtyType {
    SHAREHOUSE,//쉐어하우스
    APT,//아파트
    SINGLEROOM,//원룸
    PRIVATEHOUSE,//주택
    VILLA,//빌라
    OFFICETEL;//오피스텔
}
