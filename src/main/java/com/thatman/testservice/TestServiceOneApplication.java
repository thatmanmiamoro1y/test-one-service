package com.thatman.testservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;


//@EnableEurekaClient
//@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.thatman.testservice.mapper")
public class TestServiceOneApplication {


    public static void main(String[] args) {
        SpringApplication.run(TestServiceOneApplication.class, args);
    }

}
