package com.thatman.testservice.service.impl;

import com.thatman.server10.Entity.User;
import com.thatman.server10.client.Server20ServerClient;
import com.thatman.server10.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    Server20ServerClient server20ServerClient;


    @Override
    public User getUser() {
        User user=new User();
        System.out.println("------------------->>"+user.getUserName());
        System.out.println("------------------->>"+user.getUserPassword());
        return server20ServerClient.getUser();
    }
}
