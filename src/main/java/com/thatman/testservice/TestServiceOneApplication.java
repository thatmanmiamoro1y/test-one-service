package com.thatman.testservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients

@MapperScan(basePackages = "com.thatman.testservice.dao")
public class TestServiceOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestServiceOneApplication.class, args);
    }

}
