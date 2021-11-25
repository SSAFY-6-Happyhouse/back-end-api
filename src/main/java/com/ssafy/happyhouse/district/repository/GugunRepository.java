package com.ssafy.happyhouse.district.repository;

import com.ssafy.happyhouse.district.entity.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GugunRepository extends JpaRepository<Gugun, Long> {
    List<Gugun> findAllByGugunCodeStartingWith(String gugunCode);
}
