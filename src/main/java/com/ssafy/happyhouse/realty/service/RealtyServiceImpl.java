package com.ssafy.happyhouse.realty.service;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.district.repository.DongRepository;
import com.ssafy.happyhouse.realty.entity.*;
import com.ssafy.happyhouse.realty.model.*;
import com.ssafy.happyhouse.realty.repository.RealtyPictureRepository;
import com.ssafy.happyhouse.realty.repository.RealtyRepository;
import com.ssafy.happyhouse.spot.entity.Segwon;
import com.ssafy.happyhouse.spot.service.SpotService;
import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.repository.UserRepository;
import com.ssafy.happyhouse.util.api.KakaoMap;
import com.ssafy.happyhouse.util.file.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    private final ModelMapper modelMapper;
    private final KakaoMap kakaoMap;
    private final SpotService spotService;
    //정석은 controller - service - servicimpl = 권한문제 발생할 수 있으므로

    @Override
    public Dong checkValidDong(String dongstr, List<String> dongValues) {//유효한 동Name인지 체크
            StringTokenizer tk = new StringTokenizer(dongstr);
            for(int i=0;i<3;i++){
                dongValues.add(tk.nextToken());//0:서울시 1:성동구 2:응봉동
            }
            List<Dong> dong = dongRepository.findAllByDongName(dongValues.get(2));
            //dong객체로 sidoName,gugunName과 동일하면 realty객체에 Dong객체를 넣어줘라.
            for(int i=0;i<dong.size();i++) {
                Dong ddong = dong.get(i);
                String sidoName = ddong.getSidoName();
                String gugunName = ddong.getGugunName();

                if (sidoName.equals(dongValues.get(0)) && gugunName.equals(dongValues.get(1))) {
                    return ddong;
                }
            }
        return null;
    }
    @Override
    public String saveImage(List<MultipartFile> multipartFile, Long realtyId) throws Exception {
        Realty realty = realtyRepository.findById(realtyId).get();
        try{
           List<String> locations = fileHandler.parseFileInfo(multipartFile);
            realtyPictureRepository.saveAll(
                    locations.stream().map(location ->
                                    RealtyPicture.builder()
                                            .location(location)
                                            .realty(realty).build())
                            .collect(Collectors.toList()));
        } catch (Exception e){
            throw e;
        }
        return null;
    }

    @Override
    public void updateImage(List<MultipartFile> multipartFile,Long realtyId) throws Exception{
        try{
            Realty realty = realtyRepository.findById(realtyId).get();
            List<RealtyPicture> realtyPictures = realty.getRealtyPictures();
            realtyPictureRepository.deleteAll(realtyPictures);//삭제
            List<String> locations = fileHandler.parseFileInfo(multipartFile);//새롭게 받아오기
            realtyPictureRepository.saveAll(
                    locations.stream().map(location ->
                            RealtyPicture.builder()
                                    .location(location)
                                    .realty(realty).build())
                            .collect(Collectors.toList()));
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public String saveRealty(RealtyDto realtyDto,String username) throws Exception{
        try{
            Realty realty = realtyDto.toEntity();//애초에 Entity로 만들어놓고 Entity setter를 이용해 넣기
            User user = userRepository.findByUsername(username).get();//user객체 갖고오기

            List<Option> options = new ArrayList<>();
            List<Long> optionValues = realtyDto.getOptions();
            List<Segwon> segwons = new ArrayList<>();
            String dongstr = realtyDto.getDongstr();//서울특별시 성동구 응봉동
            List<String> dongValues = new ArrayList<>();

            RealtyType realtyType = RealtyType.values()[realtyDto.getRealtyType().intValue()]; //매물 형태
            ContractProcess contractProcess = ContractProcess.INPROCESS; //거래 상황
            ContractType contractType = ContractType.values()[realtyDto.getContractType().intValue()];//거래 형태
            log.info("NOT NULL");

            for(int i = 0 ; i< optionValues.size(); i++){ //옵션 리스트로 받아오기
                options.add(Option.values()[optionValues.get(i).intValue()]);
            }
            log.info("NOT NULL");
//            for(int i=0;i<segwonValues.size();i++){//땡세권 받아오기
//                segwons.add(Segwon.values()[segwonValues.get(i).intValue()]);
//            }
            Coordinate coordinate = kakaoMap.getCoordinatesFromAddress(realtyDto.getDongstr());


            Dong dong = checkValidDong(dongstr,dongValues);
            log.info("NOT NULL");

            if(ObjectUtils.isEmpty(dong)) { //비어있다면 exception으로 끊어버리
                try{
                    dong = dongRepository.findByDongCode(coordinate.getDongCode()).get();
                }catch (Exception e){
                    throw e;
                }
            }
            realty.setDong(dong);
            realty.setLatitude(coordinate.getLatitude());
            realty.setLongitude(coordinate.getLongitude());

            log.info(spotService.getSegwonList(coordinate.getLongitude(),coordinate.getLatitude()).toString());
            realty.setSegwons(spotService.getSegwonList(coordinate.getLongitude(),coordinate.getLatitude()));

            realty.setOptions(options);
            realty.setSegwons(segwons);
            realty.setRealtyType(realtyType);
            realty.setContractProcess(contractProcess);
            realty.setContractType(contractType);
            realty.setRegisterer(user);

            realtyRepository.save(realty); //dto에서는 service, repository layer에선 entity객체가 들어간다. 없으면 null이 들어감.
        }catch (Exception e){
                throw e;
        }
        return "sucess";
    }

    //id를 갖고 entity를 갖고와서 새로들어온 dto와 비교해서 바꿔주고, update가 아닌 save를 다시 해준다.
    @Override
    public RealtyResponseDto updateRealty(RealtyDto realtyDto) throws Exception{
        Realty realty = realtyRepository.findById(realtyDto.getRealtyId()).get(); //기존의 realty
        //realty.getasd() != realtyDto.toEntity();가 라면 realty.set()
        RealtyDto check_realty = realtyDto;//새로 들어온 realtyDto
        
        String check_str = check_realty.getDongstr();//Dong 객체
        List<String> dongValues = new ArrayList<>();
        Dong dong = checkValidDong(check_str,dongValues);
        if(ObjectUtils.isEmpty(dong)) { //비어있다면 exception,즉 말이 안되는 주소라면
                throw new Exception();
        }
        //Dong
        if(!realty.getDong().getSidoName().equals(dongValues.get(0))){
            realty.getDong().setSidoName(dongValues.get(0));
        }
        if(!realty.getDong().getGugunName().equals(dongValues.get(1))){
            realty.getDong().setGugunName(dongValues.get(1));
        }
        if(!realty.getDong().getDongName().equals(dongValues.get(2))){
            realty.getDong().setDongName(dongValues.get(2));
        }
        if(!realty.getAddress().equals(check_realty.getAddress())){
            realty.setAddress(check_realty.getAddress());
        }
        if(!realty.getHeat().equals(check_realty.getHeat())){
            realty.setHeat(check_realty.getHeat());
        }
        if(realty.getSize()!=check_realty.getSize()){
            realty.setSize(check_realty.getSize());
        }
        if(realty.getRooms()!=check_realty.getRooms()){
            realty.setRooms(check_realty.getRooms());
        }
        if(realty.getBathrooms()!=check_realty.getBathrooms()){
            realty.setBathrooms(check_realty.getBathrooms());
        }
        if(realty.getElevators()!=check_realty.getElevators()){
            realty.setElevators(check_realty.getElevators());
        }
        if(realty.getPrice()!=check_realty.getPrice()){
            realty.setPrice(check_realty.getPrice());
        }
        if(!realty.getDescription().equals(check_realty.getDescription())){
            realty.setDescription(check_realty.getDescription());
        }
        //옵션 비교
        List<Long> optionValues = realtyDto.getOptions();
        List<Option> options = new ArrayList<>();
        for(int i=0;i<optionValues.size();i++){
            options.add(Option.values()[optionValues.get(i).intValue()]);
        }
        if(!Arrays.equals(realty.getOptions().toArray(),options.toArray())){
            realty.setOptions(options);
        }
        RealtyType rType = RealtyType.values()[check_realty.getRealtyType().intValue()];
        if(realty.getRealtyType()!=rType){
            realty.setRealtyType(rType);
        }
        ContractType cType = ContractType.values()[check_realty.getContractType().intValue()];
        if(realty.getContractType()!=cType){
            realty.setContractType(cType);
        }
        ContractProcess cpType = ContractProcess.values()[check_realty.getContractProcess().intValue()];
        if(realty.getContractProcess()!=cpType){
            realty.setContractProcess(cpType);
        }
        realtyRepository.save(realty);
        return getRealty(realtyDto.getRealtyId());
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
        List<RealtyPicture> realtyPicture = realty.getRealtyPictures();
        List<String> picturesLocation = new ArrayList();
        //realtyPicture에 location넣어주기
        for(int i=0;i<realtyPicture.size();i++){
            picturesLocation.add(realtyPicture.get(i).getLocation());
        }
        //세권 정보, 옵션 정보, 관심
        List<Segwon> segwons = realty.getSegwons();
        List<Option> options = realty.getOptions();

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
                .realtyPicturesLocation(picturesLocation)
                .contractProcess(realty.getContractProcess())
                .contractType(realty.getContractType())
                .realtyType(realty.getRealtyType())
                .dongName(dong.getDongName())
                .gugunName(dong.getGugunName())
                .sidoName(dong.getSidoName())
                .segwons(segwons)
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
                        .latitude(realty.getLatitude())
                        .longitude(realty.getLongitude())
                        .build()).collect(Collectors.toList());
    }
}
