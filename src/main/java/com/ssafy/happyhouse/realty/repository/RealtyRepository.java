package com.ssafy.happyhouse.realty.repository;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.realty.entity.Realty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RealtyRepository extends JpaRepository<Realty, Long> {
    List<Realty> findAllByDongOrderByHitCount(Dong dong);
    List<Realty> findAllByDong(Dong dong);

}
