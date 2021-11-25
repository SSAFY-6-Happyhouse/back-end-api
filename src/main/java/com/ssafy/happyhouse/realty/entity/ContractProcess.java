package com.ssafy.happyhouse.realty.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ContractProcess {
    AVAILABLE,
    INPROCESS,
    COMPLETED;
}
