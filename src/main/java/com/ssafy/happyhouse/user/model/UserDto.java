package com.ssafy.happyhouse.user.model;

import com.ssafy.happyhouse.user.entity.Authority;
import com.ssafy.happyhouse.user.entity.User;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String username;
    private String authority;
    private List<String> dongcode;
    private List<Integer> segwon;

    public User toEntity(){
        Set<Authority> authorities = new HashSet<>();
        authorities.add(Authority.USER);

        return User.builder()
                .name(name)
                .password(password)
                .phone(phone)
                .username(username)
                .authority(authorities)
                .isDisabled(false)
                .build();
    }

}
