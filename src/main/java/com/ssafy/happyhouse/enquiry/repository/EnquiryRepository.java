package com.ssafy.happyhouse.enquiry.repository;

import com.ssafy.happyhouse.enquiry.entity.Enquiry;
import com.ssafy.happyhouse.realty.entity.Option;
import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    public Optional<Enquiry> findByRealty(Realty realty);
}
