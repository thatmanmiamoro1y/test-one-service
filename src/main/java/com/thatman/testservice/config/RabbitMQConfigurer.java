package com.thatman.testservice.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * @Author: thatman
 * @Date: 2019/3/17 21:41
 * @Version 1.0
 */
@Configuration
@Slf4j
public class RabbitMQConfigurer {
    //任务队列
    public static  final  String WORK_QUEUE_ONE="work.queue.one";
    //队列名称  durable是否持久化  exclusive 是否排他队列 autoDelete 无消费者时是否删除队列 arguments 死信队列绑定参数
    @Bean
    public Queue workQueueOne(){
        return new Queue(WORK_QUEUE_ONE,true,false, false, null);
    }

    //fanout Exchange 交换机路由规则：发送到所有绑定到该exchange的queue
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(WORK_QUEUE_ONE);
    }
    //binding queue和exchange绑定策略：队列 交换机类型 超时时间
    @Bean
    Binding bindingExchangeOne() {
        return BindingBuilder.bind(workQueueOne()).to(fanoutExchange());
    }

}