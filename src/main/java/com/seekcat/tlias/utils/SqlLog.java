package com.seekcat.tlias.utils;

import com.seekcat.tlias.mapper.DeptMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
public class SqlLog {


    @Resource
    private LogMapper lm;

    @Transactional(propagation = Propagation.REQUIRES_NEW)//在发生传播事务的时候创建一个新的事务
    public void insertLog(Object msg){
        lm.toLog(LocalDateTime.now(),msg.toString());
    }
}
