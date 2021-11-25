package com.ssafy.happyhouse.district.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SIDO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sido {
    @Id
    @Column(name = "SIDO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sidoId;

    @Column(name = "SIDOCODE")
    private String sidoCode;

    @Column(name = "SIDONAME")
    private String sidoName;
}
