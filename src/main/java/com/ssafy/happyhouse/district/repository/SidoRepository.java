package com.ssafy.happyhouse.district.repository;

import com.ssafy.happyhouse.district.entity.Sido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SidoRepository extends JpaRepository<Sido, Long> {

}
