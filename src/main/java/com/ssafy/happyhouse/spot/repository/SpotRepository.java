package com.ssafy.happyhouse.spot.repository;

import com.ssafy.happyhouse.spot.entity.Spot;
import com.ssafy.happyhouse.spot.entity.SpotKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    Spot findByCategoryCode(SpotKeyword spot);

    @Modifying
    @Query(
            value = "truncate table spots",
            nativeQuery = true
    )
    void truncateTable();
}
