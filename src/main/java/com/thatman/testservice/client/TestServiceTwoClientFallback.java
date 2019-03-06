package com.thatman.testservice.client;


import com.thatman.testservice.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class TestServiceTwoClientFallback implements TestServiceTwoClient {
    @Override
    public User getUser() {
        User user=new User();
        user.setUserName("--------->>>TestServiceTwoClientFallback");
        user.setUserPassword("--------->>>TestServiceTwoClientFallback");
        return user;
    }
}
