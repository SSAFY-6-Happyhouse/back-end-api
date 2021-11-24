package com.ssafy.happyhouse.spot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum Direction {
    NORTH(0.0),
    WEST(270.0),
    SOUTH(180.0),
    EAST(90.0),
    NORTHWEST(315.0),
    SOUTHWEST(225.0),
    SOUTHEAST(135.0),
    NORTHEAST(45.0);
    private final Double bearing;
    Direction(Double bearing) {
        this.bearing = bearing;
    }
}
