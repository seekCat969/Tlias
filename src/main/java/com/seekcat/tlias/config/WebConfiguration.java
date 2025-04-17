package com.seekcat.tlias.config;

import com.seekcat.tlias.interceptor.Interceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置类注解@Configuration
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Resource
    private Interceptor interceptor;

//    注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                //拦截所有请求
                .addPathPatterns("/**")
                //不拦截登录请求
                .excludePathPatterns("/login");
    }
}
