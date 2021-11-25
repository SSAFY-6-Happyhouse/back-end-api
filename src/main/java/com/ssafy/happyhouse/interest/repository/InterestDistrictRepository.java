package com.ssafy.happyhouse.interest.repository;

import com.ssafy.happyhouse.interest.entity.InterestDistrict;
import com.ssafy.happyhouse.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestDistrictRepository extends JpaRepository<InterestDistrict, Long> {
    public InterestDistrict findFirstByUser(User user);
}
