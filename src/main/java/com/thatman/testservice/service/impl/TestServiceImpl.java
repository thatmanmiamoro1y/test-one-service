package com.thatman.testservice.service.impl;

import com.thatman.testservice.client.TestServiceTwoClient;
import com.thatman.testservice.mapper.UserMapper;
import com.thatman.testservice.entity.User;
import com.thatman.testservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService , ApplicationListener<WebServerInitializedEvent> {

    @Autowired
    TestServiceTwoClient testServiceTwoClient;

    @Value(value = "${userName:localhost-Name}")
    private String userName;
    @Value(value = "${userPassword:localhost-userPassword}")
    private String userPassword;

    @Autowired
    private UserMapper userMapper;
    private int port;

    @Override
    public User getUser() {
        User user=new User();
        System.out.println("--------->>>test-service-one:port:"+port);
        System.out.println("------------------->>"+this.userName);
        System.out.println("------------------->>"+this.userPassword);
        user=userMapper.selectByPrimaryKey("a2bf7de1-427c-11e9-a837-c85b7623d608");
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        user=userMapper.selectById("a2bf81c5-427c-11e9-a837-c85b7623d608");
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        return testServiceTwoClient.getUser();
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.port=webServerInitializedEvent.getWebServer().getPort();
    }
}
