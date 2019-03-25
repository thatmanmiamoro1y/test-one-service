package com.thatman.testservice.controller;

import com.thatman.testservice.entity.User;
import com.thatman.testservice.entity.WorkMessage;
import com.thatman.testservice.rabbitMQ.WorkMQ;
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
@RequestMapping(value = "/work/")
public class RabbitMQController {

    @Autowired
    WorkMQ workMQ;

    @GetMapping(value = "sendWorkMessage")
    public WorkMessage sendMessage(){
        return workMQ.sendMessage();
    }

}
