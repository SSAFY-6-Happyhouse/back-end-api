package com.ssafy.happyhouse.interest.repository;

import com.ssafy.happyhouse.interest.entity.InterestRealty;
import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRealtyRepository extends JpaRepository<InterestRealty, Long> {
    public InterestRealty findByUserAndRealty(User user, Realty realty);
    public List<Realty> findAllByUser(User user, Pageable pageable);
}
