package com.seekcat.tlias.exception;

import com.seekcat.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器@RestControllerAdvice = @Requestbody + @ControllerAdvice
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e){
        log.error(e.toString());
        return Result.error("请求失败，请联系管理员");
    }
}
