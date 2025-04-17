package com.seekcat.tlias.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class Filter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        判断是否是登录请求
        if (req.getRequestURI().contains("login")) {
            log.warn("登录放行");
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        String jwt = req.getHeader("token");

//        判断令牌是否正确
        if (!JwtUtils.parseJWT(jwt).toString().equals("{}")) {
            log.warn("令牌正确 放行");
            chain.doFilter(servletRequest, servletResponse);
        } else {

            Map<String, Object> map = new LinkedHashMap<>();
            map.put("code", Result.error("").getCode());
            map.put("msg", "NOT_LOGIN");
            map.put("data", Result.error("").getData());
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper
                    .writerWithDefaultPrettyPrinter()  //开启格式化输出
                    .writeValueAsString(map);

            log.warn("准备输出未登录json");
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(json);
        }
    }
}
