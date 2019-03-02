package com.thatman.testservice.service.impl;

import com.thatman.testservice.Entity.User;
import com.thatman.testservice.client.TestServiceTwoClient;
import com.thatman.testservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestServiceTwoClient testServiceTwoClient;


    @Override
    public User getUser() {
        User user=new User();
        System.out.println("------------------->>"+user.getUserName());
        System.out.println("------------------->>"+user.getUserPassword());
        return testServiceTwoClient.getUser();
    }
}
