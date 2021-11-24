package com.ssafy.happyhouse.district.repository;

import com.ssafy.happyhouse.district.entity.Dong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DongRepository extends JpaRepository<Dong, Long> {
    List<Dong> findAllByDongName(String DongName);
}
