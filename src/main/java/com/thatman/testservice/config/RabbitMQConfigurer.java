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

    public static final String FANOUT_EXCHANGE = "exchange.fanout";
    public static final String DIRECT_EXCHANGE = "exchange.direct";
    public static final String TOPIC_EXCHANGE = "exchange.topic";


    public static final String QUEUE_ONE = "queue.one";
    public static final String QUEUE_TWO = "queue.two";
    public static final String QUEUE_THREE = "queue.three";

    public static final String ROUTING_KEY_DIRECT = "routingKey.direct.work";
    public static final String ROUTING_KEY_TOPIC = "routingKey.topic.*";

    //队列名称  durable是否持久化  exclusive 是否排他队列 autoDelete 无消费者时是否删除队列 arguments 死信队列绑定参数
    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_ONE, true, false, false, null);
    }

    //队列名称  durable是否持久化  exclusive 是否排他队列 autoDelete 无消费者时是否删除队列 arguments 死信队列绑定参数
    @Bean
    public Queue queueTwo() {
        return new Queue(QUEUE_TWO, true, false, false, null);
    }

    //队列名称  durable是否持久化  exclusive 是否排他队列 autoDelete 无消费者时是否删除队列 arguments 死信队列绑定参数
    @Bean
    public Queue queueThree() {
        return new Queue(QUEUE_THREE, true, false, false, null);
    }

    //fanout Exchange 交换机路由规则：发送到所有绑定到该exchange的queue 忽略routing key
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    //direct Exchange 交换机路由规则：发送到指定routing key的 exchange
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    //topic Exchange 交换机路由规则：发送到模糊匹配routing key的 exchange
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    //binding queue和exchange绑定策略：队列 交换机类型 超时时间
    @Bean
    Binding bindingExchangeFanoutAndQueueOne() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeAndQueueTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeAndQueueThree() {
        return BindingBuilder.bind(queueThree()).to(fanoutExchange());
    }

//    //binding queue和exchange绑定策略：队列 交换机类型 超时时间
//    @Bean
//    Binding bindingExchangeDirect() {
//        return BindingBuilder.bind(queueTwo()).to(directExchange()).with(ROUTING_KEY_DIRECT);
//    }
//
//    //binding queue和exchange绑定策略：队列 交换机类型 超时时间
//    @Bean
//    Binding bindingExchangeTopic() {
//        return BindingBuilder.bind(queueThree()).to(topicExchange()).with(ROUTING_KEY_TOPIC);
//    }

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