package com.seekcat.tlias.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String Jwt = request.getHeader("token");

        Claims claims = JwtUtils.parseJWT(Jwt);

        if (!claims.toString().equals("{}")) {
            log.warn("准许访问{}，放行",request.getRequestURI());
            return true;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", Result.error("").getCode());
        map.put("msg", "NOT_LOGIN");
        map.put("data", Result.error("").getData());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper
                .writerWithDefaultPrettyPrinter()  //开启格式化输出
                .writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        log.warn("拦截{}访问，返回json字符串",request.getRequestURI());
        response.getWriter().write(json);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
