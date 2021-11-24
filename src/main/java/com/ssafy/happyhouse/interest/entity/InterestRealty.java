package com.ssafy.happyhouse.interest.entity;

import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "INTEREST_REALTY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterestRealty {
    @Id
    @Column(name = "INTEREST_REALTY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestRealtyId;

    @ManyToOne
    @JoinColumn(name = "REALTY_ID")
    private Realty realty;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
