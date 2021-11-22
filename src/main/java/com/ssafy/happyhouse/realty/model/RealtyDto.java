package com.ssafy.happyhouse.realty.model;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.realty.entity.ContractProcess;
import com.ssafy.happyhouse.realty.entity.ContractType;
import com.ssafy.happyhouse.realty.entity.Realty;
import com.ssafy.happyhouse.realty.entity.RealtyType;
import com.ssafy.happyhouse.user.entity.User;
import lombok.Data;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class RealtyDto {
    private Long realtyId;//매물아이디
//    private LocalDateTime availableDate;//입주가능일
//    private Dong dong;//동
    private String address;//주소
    private String heat;
    private Double size;
    private Integer rooms;
    private Integer bathrooms;
    private Integer elevators;
    private Long price;
//    private LocalTime localTime;//contact-time,연락 가능 시간
    private String description;
    private Long likes;
    private Long hitCount;
//    private LocalDateTime registerDate;
    
    //올린놈 아이디 받아오기, 쿠키에서 빼올듯
//    private User registerer;

    //dto자체로 받아서 그 안에서 valueOf()로 뽑아서 쓸 것인가??
//    private RealtyType realtyType;
//    private ContractType contractType;
//    private ContractProcess contractProcess;


//    private Point realtyPoint;

    public Realty toEntity(){
        return Realty.builder()
                .address(address)
                .availableDate(LocalDateTime.now())
                .bathrooms(bathrooms)
//                .contractProcess(contractProcess)
//                .contractType(contractType)
//                .dong(dong)
                .description(description)
                .elevators(elevators)
                .hitCount(hitCount)
                .heat(heat)
                .price(price)
                .localTime(LocalTime.now())
                .realtyId(realtyId)
//                .realtyType(realtyType)
                .rooms(rooms)
//                .registerer(registerer)
//                .realtyPoint(realtyPoint)
                .likes(likes)
                .registerDate(LocalDateTime.now())
                .size(size)
                .build();
    }

}
