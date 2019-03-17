package com.thatman.testservice.service;

import com.thatman.testservice.entity.User;

/**
 * @Author: thatman
 * @Date: 2019/3/17 22:04
 * @Version 1.0
 */
public interface RabbitService {
    User sendMessage();
    User receiveMessage(User user);
}
