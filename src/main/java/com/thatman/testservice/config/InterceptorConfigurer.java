package com.thatman.testservice.config;

import com.thatman.testservice.Interceptor.TokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: thatman
 * @Date: 2019/3/10 3:47
 * @Version 1.0
 */
@Configuration
@Slf4j
public class InterceptorConfigurer implements WebMvcConfigurer{
    /**
     * @return 登录验证拦截器
     * 自定义登录验证拦截器
     */
    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    /**
     * @param registry 配置静态资源放行
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * @param registry 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加处理拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(tokenInterceptor());
        //排除配置：排除url请求
        interceptorRegistration.excludePathPatterns("/user/login");
        interceptorRegistration.excludePathPatterns("/sys/login.json");
        interceptorRegistration.excludePathPatterns("/charts/**");
        interceptorRegistration.excludePathPatterns("/css/**");
        interceptorRegistration.excludePathPatterns("/easyUi/**");
        interceptorRegistration.excludePathPatterns("/flashPlayer/**");
        interceptorRegistration.excludePathPatterns("/font/**");
        interceptorRegistration.excludePathPatterns("/images/**");
        interceptorRegistration.excludePathPatterns("/js/**");
        interceptorRegistration.excludePathPatterns("/pages/**");
        interceptorRegistration.excludePathPatterns("/plugin/**");
        interceptorRegistration.excludePathPatterns("/index.html");
        interceptorRegistration.excludePathPatterns("/show.html");
        //配置拦截策略：拦截所有请求
        interceptorRegistration.addPathPatterns("/**");
        log.info("--------->>>InterceptorConfigurer加载完成");
    }
}