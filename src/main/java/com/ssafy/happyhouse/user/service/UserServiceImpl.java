package com.ssafy.happyhouse.user.service;

import com.ssafy.happyhouse.security.JwtTokenProvider;
import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.model.LoginDto;
import com.ssafy.happyhouse.user.model.UpdateDto;
import com.ssafy.happyhouse.user.model.UserDto;
import com.ssafy.happyhouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

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
                userRepository.save(userDto.toEntity());
            } catch (Exception e){
                throw e;
            }
        }

        return "Success";
    }

    @Override
    public UpdateDto updateUser(UpdateDto updateDto) throws Exception{ // 유저 객체를 가져와서 직접 변경뒤 save 하는 방식
        StringTokenizer st;
        User user = userRepository.findByUsername(updateDto.getUsername()).get();
        
        // equals 가 null 체크까지 하는지 확인 필요
        if(updateDto.getPassword() != null && updateDto.getPassword() != ""){ // 패스워드가 비어있거나 없지 않다면 변경한다
            user.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }
        
        if(!updateDto.getPhone().equals(user.getPhone())){ // 핸드폰번호가 수정됐다면
            user.setPhone(updateDto.getPhone());
        }
        
        // 관심 지역리스트를 초기화 한뒤 새로 받는 데이터로 다시 선언(?)
        user.getInterestDistricts().clear();

        // 세권 정보리스트 초기화 한뒤 새로 받는 데이터로 다시 선언(?)

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
}
