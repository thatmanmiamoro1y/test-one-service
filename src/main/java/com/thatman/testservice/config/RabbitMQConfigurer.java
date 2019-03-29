package com.thatman.testservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: thatman
 * @Date: 2019/3/17 21:41
 * @Version 1.0
 */
@Configuration
@Slf4j
public class RabbitMQConfigurer {

    public static final String EXCHANGE_ONE = "work.exchange.one";
    public static final String EXCHANGE_TWO = "work.exchange.two";
    public static final String EXCHANGE_THREE = "work.exchange.three";


    public static final String QUEUE_ONE = "work.queue.one";
    public static final String QUEUE_TWO = "work.queue.two";
    public static final String QUEUE_THREE = "work.queue.three";

    public static final String ROUTINGKEY_ONE = "work.routingKey.one";
    public static final String ROUTINGKEY_TWO = "work.routingKey.two";
    public static final String ROUTINGKEY_THREE = "work.routingKey.three";

    //队列名称  durable是否持久化  exclusive 是否排他队列 autoDelete 无消费者时是否删除队列 arguments 死信队列绑定参数
    @Bean
    public Queue workQueueOne() {
        return new Queue(QUEUE_ONE, true, false, false, null);
    }

    //队列名称  durable是否持久化  exclusive 是否排他队列 autoDelete 无消费者时是否删除队列 arguments 死信队列绑定参数
    @Bean
    public Queue workQueueTwo() {
        return new Queue(QUEUE_TWO, true, false, false, null);
    }

    //队列名称  durable是否持久化  exclusive 是否排他队列 autoDelete 无消费者时是否删除队列 arguments 死信队列绑定参数
    @Bean
    public Queue workQueueThree() {
        return new Queue(QUEUE_THREE, true, false, false, null);
    }

    //fanout Exchange 交换机路由规则：发送到所有绑定到该exchange的queue 忽略routing key
    @Bean
    FanoutExchange fanoutExchangeOne() {
        return new FanoutExchange(EXCHANGE_ONE);
    }

    //fanout Exchange 交换机路由规则：发送到所有绑定到该exchange的queue 忽略routing key
    @Bean
    FanoutExchange fanoutExchangeTwo() {
        return new FanoutExchange(EXCHANGE_TWO);
    }

    //fanout Exchange 交换机路由规则：发送到所有绑定到该exchange的queue 忽略routing key
    @Bean
    FanoutExchange fanoutExchangeThree() {
        return new FanoutExchange(EXCHANGE_THREE);
    }

    //binding queue和exchange绑定策略：队列 交换机类型 超时时间
    @Bean
    Binding bindingExchangeOne() {
        return BindingBuilder.bind(workQueueOne()).to(fanoutExchangeOne());
    }

    //binding queue和exchange绑定策略：队列 交换机类型 超时时间
    @Bean
    Binding bindingExchangeTwo() {
        return BindingBuilder.bind(workQueueTwo()).to(fanoutExchangeOne());
    }

    //binding queue和exchange绑定策略：队列 交换机类型 超时时间
    @Bean
    Binding bindingExchangeThree() {
        return BindingBuilder.bind(workQueueThree()).to(fanoutExchangeOne());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMandatory(true);

        // 消息返回, yml需要配置 publisher-returns: true
        //exchange->queue
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        // 消息确认, yml需要配置 publisher-confirms: true
        //sender -> exchange
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                 log.info("消息发送到exchange成功，消息Id:{}",correlationData.getId());
            } else {
                log.info("消息发送到exchange失败,原因: {}", cause);
            }
        });
        return rabbitTemplate;
    }

}