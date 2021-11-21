package com.ssafy.happyhouse.realty.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ContractType {
    //월세
    MONTHLYRENT,
    //전세
    LONGTERMRENT,
    //매매
    SALE;
}
