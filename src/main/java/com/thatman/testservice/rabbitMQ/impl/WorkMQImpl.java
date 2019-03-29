package com.thatman.testservice.rabbitMQ.impl;

import com.netflix.discovery.converters.Auto;
import com.rabbitmq.client.Channel;
import com.thatman.testservice.config.RabbitMQConfigurer;
import com.thatman.testservice.entity.User;
import com.thatman.testservice.entity.WorkMessage;
import com.thatman.testservice.mapper.UserMapper;
import com.thatman.testservice.rabbitMQ.WorkMQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author: thatman
 * @Date: 2019/3/21 21:39
 * @Version 1.0
 */
@Slf4j
@EnableScheduling
@Component
public class WorkMQImpl implements WorkMQ
{
    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    @Scheduled(cron = "0/10 * * * * ?")
    public WorkMessage sendMessage() {
        log.info("------------------->>>"+rabbitTemplate.getRoutingKey());
        WorkMessage workMessage=new WorkMessage();
        /**
         * convertAndSend参数解释
         * 第一个参数: exchange，指定交换机名称，交换机和队列可以在rabbitMQ图形洁面上进行创建
         * 第二个参数: routingKey, 指定路由key
         * 第三个参数: 传输的对象
         * 第四个参数: correlationData，指定消息唯一id
         **/
        //FANOUT
        rabbitTemplate.convertAndSend(RabbitMQConfigurer.FANOUT_EXCHANGE,"","",new CorrelationData(UUID.randomUUID().toString()));

//        //DIRECT
//        rabbitTemplate.convertAndSend(RabbitMQConfigurer.FANOUT_EXCHANGE,"","",new CorrelationData(UUID.randomUUID().toString()));
//
//        //TOPIC
//        rabbitTemplate.convertAndSend(RabbitMQConfigurer.FANOUT_EXCHANGE,"","",new CorrelationData(UUID.randomUUID().toString()));

        log.info("发送消息");
        return workMessage;
    }


    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfigurer.QUEUE_ONE)
    @Override
    public void listenWorkQueueOne(Message message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            //向MQ发送ack，消息已经被消费:该消息的index rabbit是否可以全部全部删除
            channel.basicAck(tag, false);
            log.info("Addressee queue_one--------->>>success:message={},tag={}",message.getBody().toString(),tag);
        } catch (IOException e) {
            throw new RuntimeException("Addressee queue_one--------->>>fail");
        }
    }

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfigurer.QUEUE_TWO)
    @Override
    public void listenWorkQueueTwo(Message message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            //向MQ发送ack，消息已经被消费:该消息的index rabbit是否可以全部全部删除
            channel.basicAck(tag, false);
            log.info("Addressee queue_two--------->>>success:message={},tag={}",message.getBody().toString(),tag);
        } catch (IOException e) {
            throw new RuntimeException("Addressee queue_two--------->>>fail");
        }
    }

}
