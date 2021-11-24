package com.ssafy.happyhouse.user.Controller;

import com.ssafy.happyhouse.user.model.LoginDto;
import com.ssafy.happyhouse.user.model.UserDto;
import com.ssafy.happyhouse.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path="/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<String> userSignin(@RequestBody LoginDto loginDto){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer "+userService.login(loginDto));

        return new ResponseEntity<>(userService.login(loginDto), responseHeaders, HttpStatus.OK);
    }
//    http header에 Authorization key의 Bearer ((토큰키))를 value로 response

    //조회
    @GetMapping(path = "/{user_id}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable(name = "user_id") Long userId) {
        //return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
        return null;
    }

    //삭제
    @DeleteMapping(path = "/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "user_id") Long userId) {
        try {
            userService.deleteUser(userId);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
