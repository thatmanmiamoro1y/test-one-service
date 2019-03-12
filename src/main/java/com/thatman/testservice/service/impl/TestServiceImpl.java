package com.thatman.testservice.service.impl;

import com.thatman.testservice.client.TestServiceTwoClient;
import com.thatman.testservice.mapper.UserMapper;
import com.thatman.testservice.entity.User;
import com.thatman.testservice.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TestServiceImpl implements TestService , ApplicationListener<WebServerInitializedEvent> {

    @Autowired
    TestServiceTwoClient testServiceTwoClient;

    @Autowired
    RedisTemplate redisTemplate;

    @Value(value = "${userName:localhost-Name}")
    private String userName;
    @Value(value = "${userPassword:localhost-userPassword}")
    private String userPassword;

    @Autowired
    private UserMapper userMapper;
    private int port;

    private String tokenKey="token";
    private String tokenValue="123456";
    private Long tokenTime=120l;

    @Override
    public User getUser() {
        System.out.printf("--------->>>进入service");

        User user=new User();
        log.info("--------->>>test-service-one:port:"+port);
        log.info("--------->>>test-service-one:port:"+userName);
        log.info("--------->>>test-service-one:port:"+userPassword);
        user=userMapper.selectByPrimaryKey("a2bf7de1-427c-11e9-a837-c85b7623d608");
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        user=userMapper.selectById("a2bf81c5-427c-11e9-a837-c85b7623d608");
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        log.info("存入redis值");
        redisTemplate.opsForValue().set(tokenKey,tokenValue,tokenTime, TimeUnit.SECONDS);
        log.info("读取redis值");
        System.out.printf(redisTemplate.opsForValue().get(tokenKey).toString());

        return testServiceTwoClient.getUser();
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.port=webServerInitializedEvent.getWebServer().getPort();
    }
}
