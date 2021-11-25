package com.ssafy.happyhouse.user.controller;

import com.ssafy.happyhouse.user.model.LoginDto;
import com.ssafy.happyhouse.user.model.UpdateDto;
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
    //회원정보 수정
    //관심지역, 관심태그 null로 오면 그대로 있던게 아닌, User Entity에 있던 값을 null로 바꿔줘라.
    //id,username은 바꾸지못하도록, password를 바꿀때는 , enconding을 시킨담에 바꿔라.  -> replace
    //세권과 관심 지역 수정
    @PutMapping
    public ResponseEntity<UpdateDto> updateUser(@RequestBody UpdateDto updateDto) {
        UpdateDto responseDto;
        try{
            responseDto = userService.updateUser(updateDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     return new ResponseEntity<>(updateDto, HttpStatus.OK);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<String> userSignin(@RequestBody LoginDto loginDto){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer "+userService.login(loginDto));

        return new ResponseEntity<>(userService.login(loginDto), responseHeaders, HttpStatus.OK);
    }
//    http header에 Authorization key의 Bearer ((토큰키))를 value로 response

    //조회
/*
    @GetMapping(path = "/{user_id}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable(name = "user_id") Long userId) {
        //return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
    }

    //수정
    @PutMapping(path = "/{user_id}")
    public  ResponseEntity<Void> updateUSer(@PathVariable(name = "user_id") Long userId, @RequestBody UserDto userDto) {
        userDto.setId(userId);
    }
*/

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
