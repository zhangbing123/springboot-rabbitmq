package com.zb.rabbitmq.controller;

import com.rabbitmq.client.Channel;
import com.zb.rabbitmq.config.RabbitMqConfig;
import com.zb.rabbitmq.service.ConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Zhangbing on 2018/12/1.
 */
@RestController
public class MqConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RabbitListener(queues =RabbitMqConfig.QUEUEONE)
    @RabbitHandler
    public void consumer(String message, Channel channel,@Headers Map<String,Object> map) throws IOException {
        consumerService.custome(message);
        channel.basicAck((Long)map.get(AmqpHeaders.DELIVERY_TAG),false);//确认消费

    }
}
