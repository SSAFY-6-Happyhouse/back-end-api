package com.ssafy.happyhouse.realty.repository;

import com.ssafy.happyhouse.realty.entity.Realty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealtyRepository extends JpaRepository<Realty, Long> {

}
