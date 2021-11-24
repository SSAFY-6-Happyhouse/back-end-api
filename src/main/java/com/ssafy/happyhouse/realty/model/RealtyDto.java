package com.ssafy.happyhouse.realty.model;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.realty.entity.*;
import com.ssafy.happyhouse.user.entity.User;
import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class RealtyDto {
    private Long realtyId;//매물아이디
//    private LocalDateTime availableDate;//입주가능일
    private String dong;//동 -> 문자열로 받고, Dong형으로 변경 후 Entity에 set해주자.
    private String address;//주소
    private String heat;
    private Double size;
    private Integer rooms;
    private Integer bathrooms;
    private Integer elevators;
    private Long price;
    private List<MultipartFile> files;
//    private LocalTime localTime;//contact-time,연락 가능 시간
    private String description;
    private Long likes;
    private Long hitCount;
    private List<Long> options;
    private List<Long> interests;  //관심매물은 등록할 때 필요가 없음
//    private List<Long> segwons; //segwon에게 point를 보내주면 그 포인트를 어떤 세권인지 판단해서 보내주면,어떤 땡세권인지를 save하면 됐음. 검색할때만 segwon을 받아야함.
//    private LocalDateTime registerDate;
    
    //토큰을 통해 username을 받아와서 user에 setting 해줌.
    private User registerer;
    private Long realtyType;
    private Long contractType;
    private Long contractProcess;

    //map api로 좌표 뽑아와야 됌. -> 그 지역위주로 평균값을 위해??
//    private Point realtyPoint;


    public Realty toEntity(){
        return Realty.builder()
                .address(address)
                .availableDate(LocalDateTime.now())
                .bathrooms(bathrooms)
                .description(description)
                .elevators(elevators)
                .hitCount(hitCount)
                .heat(heat)
                .price(price)
                .contactTime(LocalTime.now())
                .realtyId(realtyId)
                .rooms(rooms)
                .registerer(registerer) //여기에 user가 들어감
//                .realtyPoint(realtyPoint)
                .likes(likes)
                .registerDate(LocalDateTime.now())
                .size(size)
                .build();
    }
}
