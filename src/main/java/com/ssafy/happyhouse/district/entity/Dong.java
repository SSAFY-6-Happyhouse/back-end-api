package com.ssafy.happyhouse.district.entity;

import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.spot.entity.Spot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DONG")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Dong {
    @Id
    @Column(name = "DONG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dongId;

    @Column(name = "DONGCODE")
    private String dongCode;

    @Column(name = "SIDONAME")
    private String sidoName;

    @Column(name = "GUGUNNAME")
    private String gugunName;

    @Column(name = "DONGNAME")
    private String dongName;

    @OneToMany(mappedBy = "dong")
    private List<Realty> realties = new ArrayList<>();

    @OneToMany(mappedBy = "dong")
    private List<Spot> spots = new ArrayList<>();
}
