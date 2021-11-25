package com.ssafy.happyhouse.user.service;

import com.ssafy.happyhouse.user.model.LoginDto;
import com.ssafy.happyhouse.user.model.UpdateDto;
import com.ssafy.happyhouse.user.model.UserDto;

public interface UserService{
     String login(LoginDto loginDto);
     String saveUser(UserDto userDto);
<<<<<<< HEAD
     void updateUser (UserDto userDto);
     UserDto getUserInfo(String username);
=======
     UpdateDto updateUser (UpdateDto updateDto) throws Exception;
>>>>>>> main
     void deleteUser(Long userId);
}
