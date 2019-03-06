package com.thatman.testservice.client;


import com.thatman.testservice.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class TestServiceTwoClientFallback implements TestServiceTwoClient {
    @Override
    public User getUser() {
        User user=new User();
        user.setUserName("--------->>>TestServiceTwoClientFallback调用服务超时");
        user.setUserPassword("--------->>>TestServiceTwoClientFallback调用服务超时");
        return user;
    }
}
