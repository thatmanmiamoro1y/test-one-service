package com.thatman.testservice.service;

import com.rabbitmq.client.Channel;
import com.thatman.testservice.entity.User;
import org.springframework.amqp.core.Message;

/**
 * @Author: thatman
 * @Date: 2019/3/17 22:04
 * @Version 1.0
 */
public interface RabbitService {
    User sendMessage();
    void receiveMessage(User user, Message message, Channel channel);
}
