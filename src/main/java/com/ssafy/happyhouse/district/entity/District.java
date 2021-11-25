package com.ssafy.happyhouse.district.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DISTRICTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class District {
    @Id
    @Column(name = "DISTRICT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;

    @Column(name = "SIDONAME")
    private String sidoName;

    @Column(name = "GUGUNNAME")
    private String gugunName;

    @Column(name = "DONGNAME")
    private String dongName;

    @OneToOne
    @JoinColumn(name = "DONGCODE",referencedColumnName = "DONGCODE")
    private Dong dong;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lng")
    private double lng;

}
