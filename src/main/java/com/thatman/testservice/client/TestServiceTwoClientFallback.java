package com.thatman.testservice.client;


import com.thatman.testservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TestServiceTwoClientFallback implements TestServiceTwoClient {
    @Override
    public User getUser() {
        User user=new User();
        user.setName("--------->>>TestServiceTwoClientFallback调用服务超时");
        user.setPassword("--------->>>TestServiceTwoClientFallback调用服务超时");
        return user;
    }
}
