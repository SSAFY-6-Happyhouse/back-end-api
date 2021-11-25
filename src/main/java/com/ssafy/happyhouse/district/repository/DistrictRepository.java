package com.ssafy.happyhouse.district.repository;

import com.ssafy.happyhouse.district.entity.District;
import com.ssafy.happyhouse.district.entity.Dong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findDistrictByDongDongCode(String dongCode);
}
