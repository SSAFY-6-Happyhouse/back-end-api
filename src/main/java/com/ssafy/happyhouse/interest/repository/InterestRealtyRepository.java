package com.ssafy.happyhouse.interest.repository;

import com.ssafy.happyhouse.interest.entity.InterestRealty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRealtyRepository extends JpaRepository<InterestRealty, Long> {
    Optional<InterestRealty> findByRealtyId(Long RealtyId);
}
