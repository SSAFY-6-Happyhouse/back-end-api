package com.ssafy.happyhouse.user.service;

import com.ssafy.happyhouse.user.model.LoginDto;
import com.ssafy.happyhouse.user.model.UserDto;

public interface UserService{
     String login(LoginDto loginDto);
     String saveUser(UserDto userDto);
     void updateUser (UserDto userDto);
     void deleteUser(Long userId);

}
