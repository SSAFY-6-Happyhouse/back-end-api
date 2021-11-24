package com.ssafy.happyhouse.realty.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "REALTY_PICTURES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealtyPicture {
    @Id
    @Column(name = "PICTURE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "SIZE")
    private Long size;

    @ManyToOne
    @JoinColumn(name = "REALTY_ID")
    private Realty realty;
}
