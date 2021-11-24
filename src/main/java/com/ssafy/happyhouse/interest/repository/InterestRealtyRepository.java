package com.ssafy.happyhouse.interest.repository;

import com.ssafy.happyhouse.interest.entity.InterestRealty;
import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRealtyRepository extends JpaRepository<InterestRealty, Long> {
    public InterestRealty findByUserAndRealty(User user, Realty realty);
    public List<Realty> findAllByUser(User user, Pageable pageable);
}
