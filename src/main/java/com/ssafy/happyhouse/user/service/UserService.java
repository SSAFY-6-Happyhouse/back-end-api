package com.ssafy.happyhouse.user.service;

import com.ssafy.happyhouse.user.model.LoginDto;
import com.ssafy.happyhouse.user.model.UpdateDto;
import com.ssafy.happyhouse.user.model.UserDto;

public interface UserService{
     String login(LoginDto loginDto);
     String saveUser(UserDto userDto);
     UpdateDto updateUser (UpdateDto updateDto) throws Exception;
     void deleteUser(Long userId);
}
