package com.ssafy.happyhouse.district.repository;

import com.ssafy.happyhouse.district.entity.Dong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DongRepository extends JpaRepository<Dong, Long> {
    Optional<Dong> findByDongName(String DongName);
}
