package com.ssafy.happyhouse.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {


    @ExceptionHandler(Exception.class) //이 Exception이 발생하면 이게 실행된다. (에러를 너무 자세히 알려주는건 별로다)
    public String handleException(Exception ex) {
        log.error("Exception 발생 : {}", ex.getMessage());
        return "Exception";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException ex) {
        log.error("404 발생 : {}", "404 page not found");
        return "NoHandlerFoundException";
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handle500(Exception ex) {
//        log.error("500 발생 : {}", "500 internal server error");
//        return "Exception";
//    }
}
