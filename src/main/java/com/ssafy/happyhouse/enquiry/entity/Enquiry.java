package com.ssafy.happyhouse.enquiry.entity;

import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.user.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ENQUIRIES")
@Data
@Builder
public class Enquiry {
    @Id
    @Column(name = "ENQUIRY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enquiryId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "REALTY_ID")
    private Realty realty;

    @Column(name = "REALTY_PROVIDER_PHONE")
    private String realtyProviderPhone;
}
