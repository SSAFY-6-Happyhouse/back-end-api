package com.ssafy.happyhouse.district.repository;

import com.ssafy.happyhouse.district.entity.Dong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DongRepository extends JpaRepository<Dong, Long> {
    List<Dong> findAllByDongName(String DongName);
    Dong findByDongNameAndGugunName(String dong,String gugun);
    Optional<Dong> findByDongCode(String dongCode);
    List<Dong> findAllByDongCodeStartingWith(String dongCode);

//    Optional<Dong> findByDongId(Long DongId);
}
