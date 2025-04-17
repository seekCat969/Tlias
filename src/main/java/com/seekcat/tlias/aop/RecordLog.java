package com.seekcat.tlias.aop;

import com.seekcat.tlias.utils.SqlLog;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
@Aspect //AOP类
@Order(1) //控制通知执行顺序的优先级
public class RecordLog {
    @Resource
    SqlLog sqlLog;

    static final String pointCut = "execution(* com.seekcat.tlias.mapper.*.*(..))";

    @Pointcut("execution(* com.seekcat.tlias.mapper.*.*(..))")
    public void pt(){}

    @Around("pt()")//* com.seekcat.tlias.mapper.*.*(..) 第一个*为返回值
    public Object recordLogs(ProceedingJoinPoint pjp) throws Throwable {
        sqlLog.insertLog(pjp.getSignature().toString());
        return pjp.proceed();
    }
}
