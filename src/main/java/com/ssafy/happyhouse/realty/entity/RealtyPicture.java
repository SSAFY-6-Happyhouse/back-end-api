package com.ssafy.happyhouse.realty.entity;

import javax.persistence.*;

@Entity
@Table(name = "REALTY_PICTURES")
public class RealtyPicture {
    @Id
    @Column(name = "PICTURE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;

    @Column(name = "LOCATION")
    private String location;

    @ManyToOne
    @JoinColumn(name = "REALTY_ID")
    private Realty realty;
}
