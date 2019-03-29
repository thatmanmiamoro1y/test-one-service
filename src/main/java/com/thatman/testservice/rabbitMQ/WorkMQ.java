package com.thatman.testservice.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.thatman.testservice.entity.WorkMessage;
import org.springframework.amqp.core.Message;

/**
 * @Author: thatman
 * @Date: 2019/3/21 21:41
 * @Version 1.0
 */
public interface WorkMQ {
    WorkMessage sendMessage();
    void listenWorkQueueOne(Message message, Channel channel,long tag);
    void listenWorkQueueTwo(Message message, Channel channel,long tag);
}
