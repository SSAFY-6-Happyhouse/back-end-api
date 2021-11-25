package com.ssafy.happyhouse.spot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Segwon {
    마세권(1,"MT1"),
    편세권(2, "CS2"),
    학세권(3,"SC4"),
    역세권(4,"SW8"),
    문세권(5,"CT1"),
    숲세권(6,"AT4"),
    주세권(7,"FD6"),
    별세권(8,"CE7"),
    병세권(9,"HP8");
    private final int id;
    private final String code;
}
