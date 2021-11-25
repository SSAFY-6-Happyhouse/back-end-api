package com.ssafy.happyhouse.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.happyhouse.realty.model.Coordinate;
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

    @GetMapping(path = "/coordinate")
    public ResponseEntity<Coordinate> userFavorite(@RequestHeader(HttpHeaders.AUTHORIZATION)String bearerToken){
        String token = bearerToken.replace("Bearer ","");//기본적으로 header에 Bearer를 먼저 넣어주고 한다.
        DecodedJWT decodedJWT = JWT.decode(token);//디코딩
        String username = decodedJWT.getSubject();//이름 뽑아오기
        Coordinate coordinate ;
        try{
           coordinate = userService.getInterestDistrictCoordinate(username);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(coordinate, HttpStatus.OK);

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
