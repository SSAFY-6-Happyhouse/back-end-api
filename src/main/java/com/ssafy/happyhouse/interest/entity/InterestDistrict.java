package com.ssafy.happyhouse.interest.entity;

import com.ssafy.happyhouse.district.entity.District;
import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.district.entity.Gugun;
import com.ssafy.happyhouse.district.entity.Sido;
import com.ssafy.happyhouse.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "INTEREST_DISTRICT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterestDistrict {

    @Id
    @Column(name = "INTEREST_DISTRICT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestDistrictId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "DONG_ID")
    private Dong dong;
}
