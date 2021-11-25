package com.ssafy.happyhouse.realty.service;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.district.repository.DongRepository;
import com.ssafy.happyhouse.enquiry.entity.Enquiry;
import com.ssafy.happyhouse.realty.entity.*;
import com.ssafy.happyhouse.realty.model.*;
import com.ssafy.happyhouse.realty.repository.RealtyPictureRepository;
import com.ssafy.happyhouse.realty.repository.RealtyRepository;
import com.ssafy.happyhouse.spot.entity.Segwon;
import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.repository.UserRepository;
import com.ssafy.happyhouse.util.api.KakaoMap;
import com.ssafy.happyhouse.util.file.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RealtyServiceImpl implements RealtyService{
    private final RealtyRepository realtyRepository;
    private final UserRepository userRepository;
    private final DongRepository dongRepository;
    private final RealtyPictureRepository realtyPictureRepository;
    private final FileHandler fileHandler;
//    private final ModelMapper modelMapper;
    private final ModelMapper modelMapper;
    private final KakaoMap kakaoMap;
    //정석은 controller - service - servicimpl = 권한문제 발생할 수 있으므로

    @Override
    public String saveImage(List<MultipartFile> multipartFile) throws Exception {
        try{
            fileHandler.parseFileInfo(multipartFile);
        } catch (Exception e){
            throw e;
        }

        return null;
    }
    @Override
    public String saveRealty(RealtyDto realtyDto,String username) {
        try{
            Realty realty = realtyDto.toEntity();//애초에 Entity로 만들어놓고 Entity setter를 이용해 넣기
            User user = userRepository.findByUsername(username).get();//user객체 갖고오기

            List<Option> options = new ArrayList<>();
            List<Long> optionValues = realtyDto.getOptions();
            List<Segwon> segwons = new ArrayList<>();
//            List<Long> segwonValues = realtyDto.getSegwons(); -> dto에서 받는게 아닌,segwon에게 받아야한다.
            String dongstr = realtyDto.getDongstr();//서울특별시 성동구 응봉동
            List<String> dongValues = new ArrayList<>();

            RealtyType realtyType = RealtyType.values()[realtyDto.getRealtyType().intValue()]; //매물 형태
            ContractProcess contractProcess = ContractProcess.values()[realtyDto.getContractProcess().intValue()]; //거래 상황
            ContractType contractType = ContractType.values()[realtyDto.getContractType().intValue()];//거래 형태

            for(int i = 0 ; i< optionValues.size(); i++){ //옵션 리스트로 받아오기
                options.add(Option.values()[optionValues.get(i).intValue()]);
            }
//            for(int i=0;i<segwonValues.size();i++){//땡세권 받아오기
//                segwons.add(Segwon.values()[segwonValues.get(i).intValue()]);
//            }

            StringTokenizer tk = new StringTokenizer(dongstr);
            for(int i=0;i<3;i++){
                dongValues.add(tk.nextToken());//0:서울시 1:성동구 2:응봉동
            }

            List<Dong> dong = dongRepository.findAllByDongName(dongValues.get(2));
            //dong객체로 sidoName,gugunName과 동일하면 realty객체에 Dong객체를 넣어줘라.
            for(int i=0;i<dong.size();i++){
                Dong ddong = dong.get(i);
                String sidoName = ddong.getSidoName();
                String gugunName = ddong.getGugunName();
                if(sidoName.equals(dongValues.get(0))&&gugunName.equals(dongValues.get(1))){
                    realty.setDong(ddong);
                    break;
                }
            }
//            for(){
//
//            }
            realty.setOptions(options);
            realty.setSegwons(segwons);
            realty.setRealtyType(realtyType);
            realty.setContractProcess(contractProcess);
            realty.setContractType(contractType);
            realty.setRegisterer(user);

            //Coordinate coordinate = kakaoMap.getCoordinatesFromAddress(realtyDto.getDongstr(),realtyDto.getAddress());

            realtyRepository.save(realty); //dto에서는 service, repository layer에선 entity객체가 들어간다. 없으면 null이 들어감.
        }catch (Exception e){
            throw e;
        }
        return "sucess";
    }
    //id를 갖고 entity를 갖고와서 새로들어온 dto와 비교해서 바꿔주고, update가 아닌 save를 다시 해준다.
    @Override
    public RealtyDto updatePost(RealtyDto realtyDto) {
//        Realty realty = realtyRepository.findById(realtyDto.getRealtyId()).get();
//        realty =realty.updateRealty(realtyDto.toEntity());
        return null;
    }

    @Override
    public void deleteRealty(Long realtyId) {
        realtyRepository.deleteById(realtyId);
    }

    @Override
    public RealtyResponseDto getRealty(Long realtyId) {//상세조회 ,
        Realty realty = realtyRepository.findById(realtyId).get();
        realty.setHitCount(realty.getHitCount()+1);
        realty = realtyRepository.save(realty);//업데이트 된 상태
        User user = userRepository.findById(realty.getRegisterer().getUserId()).get();
        Dong dong = dongRepository.findById(realty.getDong().getDongId()).get();//Dong객체 갖고 오기
        //세권 정보, 옵션 정보, 관심
//        List<Segwon> segwons = realty.getSegwons();
        List<Option> options = realty.getOptions();
        List<RealtyPicture> realtyPictures = realty.getRealtyPictures();
        List<Enquiry> enquiries = realty.getEnquiries();

        return RealtyResponseDto.builder()
                .address(realty.getAddress())
                .username(user.getUsername()).
                availableDate(realty.getAvailableDate())
                .bathrooms(realty.getBathrooms())
                .description(realty.getDescription())
                .elevators(realty.getElevators())
                .hitCount(realty.getHitCount())
                .heat(realty.getHeat())
                .price(realty.getPrice())
                .contactTime(realty.getContactTime())
                .rooms(realty.getRooms())
                .registerer_id(realty.getRegisterer().getUserId())
                .likes(realty.getLikes())
                .registerDate(realty.getRegisterDate())
                .size(realty.getSize())
                .options(options)
                .realtyPictures(realtyPictures)
                .enquiries(enquiries)
                .contractProcess(realty.getContractProcess())
                .contractType(realty.getContractType())
                .realtyType(realty.getRealtyType())
                .dongName(dong.getDongName())
                .gugunName(dong.getGugunName())
                .sidoName(dong.getSidoName())
                .build();
    }

    @Override
    public List<Marker> getRecommendedMarkers(String username) {
        User user = userRepository.findByUsername(username).get();

        Dong interestedDong = user.getInterestDistricts().get(0).getDong();

        List<Realty> realties = realtyRepository.findAllByDongOrderByHitCount(interestedDong);

        return realties.stream().limit(5).map(realty ->
                Marker.builder()
                        .imgPath(realty.getRealtyPictures().get(0).getLocation())
                        .price(realty.getPrice())
                        .contractType(realty.getContractType())
                        .realtyType(realty.getRealtyType())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<RealtyDto> getRealtyList() {
        return null;
    }

    @Override
    public List<Marker> getRealtyMarkers() {
        List<Realty> realties = realtyRepository.findAll();
        //좌표에 맞게 search 해주기

        //좌표 같이 보내주기
        return realties.stream().map(realty ->
                Marker.builder()
                        .realtyType(realty.getRealtyType())
                        .contractType(realty.getContractType())
                        .segwons(realty.getSegwons())
                        .price(realty.getPrice())
                        .build()).collect(Collectors.toList());
    }


}
