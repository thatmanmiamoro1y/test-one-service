package com.thatman.testservice.rabbitMQ.impl;

import com.rabbitmq.client.Channel;
import com.thatman.testservice.config.RabbitMQConfigurer;
import com.thatman.testservice.entity.WorkMessage;
import com.thatman.testservice.rabbitMQ.WorkMQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: thatman
 * @Date: 2019/3/21 21:39
 * @Version 1.0
 */
@Slf4j
@EnableScheduling
@Component
public class WorkMQImpl implements WorkMQ {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    @Scheduled(cron = "0/10 * * * * ?")
    public WorkMessage sendMessage() {
        log.info("发送work消息--------->>>start");
        WorkMessage workMessage=new WorkMessage();
        /**
         * convertAndSend参数解释
         * 第一个参数: exchange，指定交换机名称，交换机和队列可以在rabbitMQ图形洁面上进行创建
         * 第二个参数: routingKey, 指定路由key
         * 第三个参数: 传输的对象
         * 第四个参数: correlationData，指定消息唯一id
         **/
        rabbitTemplate.convertAndSend(RabbitMQConfigurer.WORK_QUEUE_ONE,workMessage);
        log.info("发送work消息--------->>>end");
        return workMessage;
    }

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfigurer.WORK_QUEUE_ONE)
    @Override
    public void listenWorkQueueOne(Message message, Channel channel) {
//        try {
            //向MQ发送ack，消息已经被消费:该消息的index rabbit是否可以全部全部删除
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("消费者one--------->>>success:"+message.toString());
//        } catch (IOException e) {
//            throw new RuntimeException("消费者one--------->>>fail");
//        }
    }

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfigurer.WORK_QUEUE_ONE)
    @Override
    public void listenWorkQueueTwo(Message message, Channel channel) {
        try {
            //向MQ发送ack，消息已经被消费:该消息的index rabbit是否可以全部全部删除
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("消费者two--------->>>success:"+message.toString());
        } catch (IOException e) {
            throw new RuntimeException("消费者two--------->>>fail");
        }
    }
}
