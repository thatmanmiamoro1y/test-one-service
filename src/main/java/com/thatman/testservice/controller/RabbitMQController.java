package com.thatman.testservice.controller;

import com.thatman.testservice.entity.User;
import com.thatman.testservice.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: thatman
 * @Date: 2019/3/17 22:00
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/rabbit/")
public class RabbitMQController {

    @Autowired
    RabbitService rabbitService;
    @GetMapping(value = "sendMessage")
    public User sendMessage(){
        return rabbitService.sendMessage();
    }

}
