package com.ssafy.happyhouse.interest.repository;

import com.ssafy.happyhouse.interest.entity.InterestDistrict;
import com.ssafy.happyhouse.interest.model.InterestDistrictDto;
import com.ssafy.happyhouse.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestDistrictRepository extends JpaRepository<InterestDistrict, Long> {
    public InterestDistrict findFirstByUser(User user);
}
