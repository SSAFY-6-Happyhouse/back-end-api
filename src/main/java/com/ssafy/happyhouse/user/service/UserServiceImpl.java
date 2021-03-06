package com.ssafy.happyhouse.user.service;

import com.ssafy.happyhouse.district.dto.DongDto;
import com.ssafy.happyhouse.district.entity.District;
import com.ssafy.happyhouse.district.entity.Dong;
import com.ssafy.happyhouse.district.repository.DistrictRepository;
import com.ssafy.happyhouse.district.repository.DongRepository;
import com.ssafy.happyhouse.interest.entity.InterestDistrict;
import com.ssafy.happyhouse.interest.model.InterestDistrictDto;
import com.ssafy.happyhouse.interest.repository.InterestDistrictRepository;
import com.ssafy.happyhouse.realty.model.Coordinate;
import com.ssafy.happyhouse.security.JwtTokenProvider;
import com.ssafy.happyhouse.spot.entity.Segwon;
import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.model.LoginDto;
import com.ssafy.happyhouse.user.model.UpdateDto;
import com.ssafy.happyhouse.user.model.UserDto;
import com.ssafy.happyhouse.user.model.UserResponseDto;
import com.ssafy.happyhouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final InterestDistrictRepository interestDistrictRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final DongRepository dongRepository;
    private final DistrictRepository districtRepository;

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername()).get();
        if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            return jwtTokenProvider.createToken(new UsernamePasswordAuthenticationToken(user.getUsername(),"",user.getAuthority()));
        }else{
            return null;
        }
    }

    @Override
    public String saveUser(UserDto userDto) {
        if(!userRepository.existsByUsername(userDto.getUsername())){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            try {
//            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                User user = userDto.toEntity();
                user = userRepository.save(user);
                List<InterestDistrict> interestDistricts = new ArrayList<>();

                for(String dongCode : userDto.getDongcode()){

                    Dong dong = dongRepository.findByDongCode(dongCode).get();
                    InterestDistrict interestDistrict= interestDistrictRepository.save(
                            InterestDistrict.builder()
                                    .dong(dong)
                                    .user(user)
                                    .build());

                    interestDistricts.add(interestDistrict);
                }
                List<Segwon> segwons = new ArrayList<>();

                for(Integer segwonId : userDto.getSegwon()){
                    segwons.add(Segwon.values()[segwonId]);
                }
                user.setSegwons(segwons);
                user.setInterestDistricts(interestDistricts);
                userRepository.save(user);
            } catch (Exception e){
                throw e;
            }
        }

        return "Success";
    }

    @Override
    public UpdateDto updateUser(UpdateDto updateDto) throws Exception{ // ?????? ????????? ???????????? ?????? ????????? save ?????? ??????
        StringTokenizer st;
        User user = userRepository.findByUsername(updateDto.getUsername()).get();
        
        // equals ??? null ???????????? ????????? ?????? ??????
        if(updateDto.getPassword() != null && updateDto.getPassword() != ""){ // ??????????????? ??????????????? ?????? ????????? ????????????
            user.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }
        
        if(!updateDto.getPhone().equals(user.getPhone())){ // ?????????????????? ???????????????
            user.setPhone(updateDto.getPhone());
        }
        
        // ?????? ?????????????????? ????????? ?????? ?????? ?????? ???????????? ?????? ??????(?)
        user.getInterestDistricts().clear();

        // ?????? ??????????????? ????????? ?????? ?????? ?????? ???????????? ?????? ??????(?)

        userRepository.save(user);
        return updateDto;
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public UserResponseDto getUserInfo(String username) {
        User user = userRepository.findByUsername(username).get();
        UserResponseDto userResponseDto;

        try{
             userResponseDto = UserResponseDto.builder()
                    .userId(user.getUserId())
                    .username(user.getUsername())
                    .segwons(user.getSegwons())
                    .interestDistricts(
                            user.getInterestDistricts()
                                    .stream().map(interestDistrict ->
                                            DongDto.builder().dongName(interestDistrict.getDong().getDongName())
                                                    .gugunName(interestDistrict.getDong().getGugunName())
                                                    .dongId(interestDistrict.getDong().getDongId())
                                                    .dongCode(interestDistrict.getDong().getDongCode())
                                                    .sidoName(interestDistrict.getDong().getSidoName())
                                                    .build()
                                                    ).collect(Collectors.toList())).build();
        }catch (Exception e){
            throw e;
        }

        return userResponseDto;
    }

    @Override
    public Coordinate getInterestDistrictCoordinate(String username) {
        User user = userRepository.findByUsername(username).get();
        District district;
        if(CollectionUtils.isEmpty(user.getInterestDistricts())) {
            Dong dong = dongRepository.findByDongNameAndGugunName("?????????", "?????????");
            district = districtRepository
                    .findDistrictByDongDongCode(dong.getDongCode()).get();
        }
        else{
            InterestDistrict interestDistrict = user.getInterestDistricts().get(0);

            log.info("HIHI");
            district = districtRepository
                    .findDistrictByDongDongCode(
                            interestDistrict.getDong().getDongCode()).get();
            log.info("HIHI");
        }

        return Coordinate.builder()
                .latitude(district.getLat())
                .longitude(district.getLng())
                .build();
    }
}
