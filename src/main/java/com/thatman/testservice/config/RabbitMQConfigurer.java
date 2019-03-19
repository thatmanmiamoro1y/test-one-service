package com.thatman.testservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: thatman
 * @Date: 2019/3/17 21:41
 * @Version 1.0
 */
@Configuration
public class RabbitMQConfigurer {
    //任务队列
    public static  final  String WORK_QUEUE="work.queue";

    //队列名称  durable是否持久化
    @Bean
    public Queue workQueue(){
        return new Queue(WORK_QUEUE,true);
    }

}