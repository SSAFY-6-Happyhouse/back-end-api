package com.ssafy.happyhouse.realty.model;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.enquiry.entity.Enquiry;
import com.ssafy.happyhouse.realty.entity.*;
import com.ssafy.happyhouse.spot.entity.Segwon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealtyResponseDto {
    private String address;
    private String heat;
    private Double size;
    private Integer rooms;
    private Integer bathrooms;
    private Integer elevators;
    private Long price;
    private String description;
    private Long likes;
    private Long hitCount;
    private Long registerer_id;
    private String username;

    private RealtyType realtyType;
    private ContractType contractType;
    private ContractProcess contractProcess;

    private String sidoName;
    private String gugunName;
    private String dongName;

    private Double latitude;
    private Double longitude;

    private List<Option> options;
    private List<Segwon> segwons;
    private List<RealtyPicture> realtyPictures;
    private List<Enquiry> enquiries;

    private LocalDateTime availableDate;
    private LocalDateTime registerDate;
    private LocalTime contactTime;
}
