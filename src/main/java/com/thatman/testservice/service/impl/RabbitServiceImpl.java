package com.thatman.testservice.service.impl;

import com.rabbitmq.client.Channel;
import com.thatman.testservice.config.RabbitMQConfigurer;
import com.thatman.testservice.entity.User;
import com.thatman.testservice.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: thatman
 * @Date: 2019/3/17 22:04
 * @Version 1.0
 */
@Service
@Slf4j
@EnableScheduling
public class RabbitServiceImpl implements RabbitService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    @Scheduled(cron = "0/5 * * * * ?")
    public User sendMessage() {
        User user=new User();
        user.setName("thatman");
        user.setName("miamoto1y");
        log.info("--------->>>rabbitMQ send:"+user);
        /**
         * convertAndSend参数解释
         * 第一个参数: exchange，指定交换机名称，交换机和队列可以在rabbitMQ图形洁面上进行创建
         * 第二个参数: routingKey, 指定路由key
         * 第三个参数: 传输的对象
         * 第四个参数: correlationData，指定消息唯一id
         **/
        rabbitTemplate.convertAndSend(RabbitMQConfigurer.WORK_QUEUE,user);
        log.info("send--------->>>发送消息成功:"+user);
        return user;
    }
    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfigurer.WORK_QUEUE)
    @Override
    public void receiveMessage(User user, Message message, Channel channel) {
        try {
            //向MQ发送ack，消息已经被消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("receive--------->>>消费消息成功:"+user);
        } catch (IOException e) {
            throw new RuntimeException("处理消息失败");
        }
    }
}
