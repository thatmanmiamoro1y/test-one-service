package com.thatman.testservice.client;


import com.thatman.testservice.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class TestServiceThreeClientFallback implements TestServiceThreeClient {
    @Override
    public User getUser() {
        User user=new User();
        user.setUserName("调用服务超时");
        user.setUserPassword("调用服务超时");
        return user;
    }
}
