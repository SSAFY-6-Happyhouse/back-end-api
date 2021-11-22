package com.ssafy.happyhouse.realty.entity;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.enquiry.entity.Enquiry;
import com.ssafy.happyhouse.interest.entity.InterestRealty;
import com.ssafy.happyhouse.spot.entity.Segwon;
import com.ssafy.happyhouse.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REALTIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Realty {
    @Id
    @Column(name = "REALTY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long realtyId;

    @Column(name = "AVAILABlE_DATE")
    private LocalDateTime availableDate;

    //도동코드를 묶어줄 수 있어야하나??
    @ManyToOne
    @JoinColumn(name = "DONG_ID")
    private Dong dong;

    //상세 주소
    @Column(name = "ADDRESS")
    private String address;

    //옵션 관련
    @Column(name = "HEAT")
    private String heat;

    @Column(name = "SIZE")
    private Double size;

    @Column(name = "ROOMS")
    private Integer rooms;

    @Column(name = "BATHROOMS")
    private Integer bathrooms;

    @Column(name = "ELEVATORS")
    private Integer elevators;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "CONTACT_TIME")
    private LocalTime localTime;

    @Column(name = "DESCRIPTION")
    private String description;

    //찜 갯수
    @Column(name = "LIKES")
    private Long likes;

    //조회수
    @Column(name = "HIT_COUNT")
    private Long hitCount;
    
    //등록 날짜
    @Column(name = "REGISTER_DATE")
    private LocalDateTime registerDate;

    //등록한 사람, Joincolumn= 외래키 맵핑, name에 매핑할 외래 키 이름 지정
    @ManyToOne
    @JoinColumn(name = "REGISTERER_ID")
    private User registerer;

    @Enumerated(EnumType.STRING)
    private RealtyType realtyType;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @Enumerated(EnumType.STRING)
    private ContractProcess contractProcess;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "REALTY_SEGWONS",
            joinColumns = @JoinColumn(name = "REALTY_ID")
    )
    private List<Segwon> segwons = new ArrayList<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "REALTY_OPTIONS",
            joinColumns = @JoinColumn(name = "REALTY_ID")
    )
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "realty")
    private List<Enquiry> enquiries;

    @OneToMany(mappedBy = "realty")
    private List<InterestRealty> interestRealties = new ArrayList<>();

    @OneToMany(mappedBy = "realty")
    private List<RealtyPicture> realtyPictures = new ArrayList<>();

    //좌표
    @Column(name = "REALTY_POINT")
    private Point realtyPoint;

    //매물 내용 수정
    public Realty updateRealty(Realty updatedRealty){
        if(StringUtils.hasText(updatedRealty.getAddress())){
            this.address = updatedRealty.getAddress();
        }
        if(StringUtils.hasText(updatedRealty.getDescription())){
            this.description = updatedRealty.getDescription();
        }
        if(StringUtils.hasText(updatedRealty.getHeat())){
            this.heat = updatedRealty.getHeat();
        }

        return this;
    }
}
