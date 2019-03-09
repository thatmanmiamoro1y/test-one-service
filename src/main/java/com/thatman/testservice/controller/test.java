package com.thatman.testservice.controller;

import com.thatman.testservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test/")
public class test {

    @Autowired
    TestService testService;

    @GetMapping(value = "getUser")
    public String getUser(){
        testService.getUser();
        return "test";
    }
}
