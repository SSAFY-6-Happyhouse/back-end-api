package com.ssafy.happyhouse.user.entity;

import com.ssafy.happyhouse.enquiry.entity.Enquiry;
import com.ssafy.happyhouse.interest.entity.InterestDistrict;
import com.ssafy.happyhouse.interest.entity.InterestRealty;
import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.spot.entity.Segwon;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "phone")
    private String phone;

    //등록 매물
    @OneToMany(mappedBy = "registerer")
    private List<Realty> realties;

    //관심 지역
    @OneToMany(mappedBy = "user")
    private List<InterestDistrict> interestDistricts;

    //관심 매물
    @OneToMany(mappedBy = "user")
    private List<InterestRealty> interestRealties;

    //관심 태그(세권)
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "INTERESTED_SEGWONS",
            joinColumns = @JoinColumn(name = "USER_ID")
    )
    private List<Segwon> segwons = new ArrayList<>();

    //문의
    @OneToMany(mappedBy = "user")
    private List<Enquiry> enquiries;

    //신고
    @OneToMany(mappedBy = "user")
    private List<Report> reports;


}
