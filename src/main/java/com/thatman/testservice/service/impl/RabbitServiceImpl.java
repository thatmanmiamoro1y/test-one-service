package com.thatman.testservice.service.impl;

import com.thatman.testservice.config.RabbitMQConfigurer;
import com.thatman.testservice.entity.User;
import com.thatman.testservice.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: thatman
 * @Date: 2019/3/17 22:04
 * @Version 1.0
 */
@Service
@Slf4j
public class RabbitServiceImpl implements RabbitService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public User sendMessage() {
        User user=new User();
        user.setName("thatman");
        user.setName("miamoto1y");
        log.info("--------->>>rabbitMQ send:"+user);
        rabbitTemplate.convertAndSend(RabbitMQConfigurer.FANOUT_EXCHANGE,"",user);
        return user;
    }
    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfigurer.FANOUT_QUEUE1)
    @Override
    public User receiveMessage(User user) {
        log.info("--------->>>receiveFanout1监听到消息" + user);
        return user;
    }
}
