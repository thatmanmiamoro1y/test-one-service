package com.thatman.testservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thatman.testservice.client.TestServiceTwoClient;
import com.thatman.testservice.dao.UserMapper;
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
        user.setName("thatman");
        user=userMapper.selectOne(new QueryWrapper<User>().eq("id","1"));
        System.out.printf(user.getName());
        System.out.println(user.getPassword());
        return testServiceTwoClient.getUser();
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.port=webServerInitializedEvent.getWebServer().getPort();
    }
}
