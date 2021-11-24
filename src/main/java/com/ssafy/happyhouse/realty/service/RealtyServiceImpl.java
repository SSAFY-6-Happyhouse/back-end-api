package com.ssafy.happyhouse.realty.service;

import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.district.repository.DongRepository;
import com.ssafy.happyhouse.realty.entity.*;
import com.ssafy.happyhouse.realty.model.RealtyDto;
import com.ssafy.happyhouse.realty.model.RealtyPicturesDto;
import com.ssafy.happyhouse.realty.repository.RealtyPictureRepository;
import com.ssafy.happyhouse.realty.repository.RealtyRepository;
import com.ssafy.happyhouse.spot.entity.Segwon;
import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
@Slf4j
public class RealtyServiceImpl implements RealtyService{
    private final RealtyRepository realtyRepository;
    private final UserRepository userRepository;
    private final DongRepository dongRepository;
    private final RealtyPictureRepository realtyPictureRepository;
//    private final ModelMapper modelMapper;
    //정석은 controller - service - servicimpl = 권한문제 발생할 수 있으므로

    @Override
    public String saveRealty(RealtyDto realtyDto,String username) {
        try{
            Realty realty = realtyDto.toEntity();//애초에 Entity로 만들어놓고 Entity setter를 이용해 넣기
            User user = userRepository.findByUsername(username).get();//user객체 갖고오기

            List<Option> options = new ArrayList<>();
            List<Long> optionValues = realtyDto.getOptions();
            List<Segwon> segwons = new ArrayList<>();
//            List<Long> segwonValues = realtyDto.getSegwons(); -> dto에서 받는게 아닌,segwon에게 받아야한다.
            String dongstr = realtyDto.getDong();//서울특별시 성동구 응봉동
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
    public RealtyDto getRealty(Long realtyId) {
        return null;
    }

    @Override
    public List<RealtyDto> getRealtyList() {
        return null;
    }

    @Override
    public Long saveFile(RealtyPicturesDto realtyPictureDto) {
        RealtyPicture realtyPicture = realtyPictureDto.toEntity();
        return realtyPictureRepository.save(realtyPicture).getPictureId();
    }

    @Override
    public RealtyPicturesDto getFile(Long id) {
        return null;
    }
}
