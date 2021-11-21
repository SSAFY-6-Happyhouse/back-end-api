package com.ssafy.happyhouse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping
@Slf4j
public class HomeController {

    @GetMapping
    public ResponseEntity<LocalDateTime> test(){
        log.info("HIHI");
        return new ResponseEntity<>(LocalDateTime.now(), HttpStatus.OK);
    }
}
