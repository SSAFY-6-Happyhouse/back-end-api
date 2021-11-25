package com.ssafy.happyhouse.user.service;

import com.ssafy.happyhouse.district.repository.DongRepository;
import com.ssafy.happyhouse.security.JwtTokenProvider;
import com.ssafy.happyhouse.user.entity.User;
import com.ssafy.happyhouse.user.model.LoginDto;
import com.ssafy.happyhouse.user.model.UserDto;
import com.ssafy.happyhouse.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final DongRepository dongRepository;

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
//                User user = userDto.toEntity();

                List<String> dong = new ArrayList<>();

                List<String> distrincts = new ArrayList<String>();
                String address = String.join(" ", distrincts);

                StringTokenizer st = new StringTokenizer(address);
                for(int i = 0; i < 3; i++) {
                    dong.add(st.nextToken());
                }
                userRepository.save(userDto.toEntity());
            } catch (Exception e){
                throw e;
            }
        }

        return "Success";
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername()).get();
    }

    @Override
    public UserDto getUserInfo(String username) {
        User user = userRepository.findByUsername(username).get();
       // UserDto userDto =
        return null;
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
