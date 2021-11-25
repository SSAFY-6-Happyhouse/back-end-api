package com.ssafy.happyhouse.user.service;

import com.ssafy.happyhouse.realty.model.Coordinate;
import com.ssafy.happyhouse.user.model.LoginDto;
import com.ssafy.happyhouse.user.model.UpdateDto;
import com.ssafy.happyhouse.user.model.UserDto;
import com.ssafy.happyhouse.user.model.UserResponseDto;

public interface UserService{
     String login(LoginDto loginDto);
     String saveUser(UserDto userDto);
     UpdateDto updateUser (UpdateDto updateDto) throws Exception;
     void deleteUser(Long userId);
     UserResponseDto getUserInfo(String username);
     Coordinate getInterestDistrictCoordinate(String username);
}
