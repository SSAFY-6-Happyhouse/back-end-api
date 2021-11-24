package com.ssafy.happyhouse.spot.repository;

import com.ssafy.happyhouse.spot.entity.Spot;
import com.ssafy.happyhouse.spot.entity.SpotKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    Spot findByCategoryCode(SpotKeyword spot);
}
