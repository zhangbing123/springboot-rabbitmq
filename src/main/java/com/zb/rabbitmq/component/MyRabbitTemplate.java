package com.zb.rabbitmq.component;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 操作消息的组件
 */
@Component
public class MyRabbitTemplate {

    @Autowired RabbitTemplate rabbitTemplate;

    public void convertAndSend(String exchange, String routingKey, Object object, CorrelationData correlationData, RabbitTemplate.ConfirmCallback confirmCallback) throws AmqpException {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.convertAndSend(exchange, routingKey, object, correlationData);
    }
}
