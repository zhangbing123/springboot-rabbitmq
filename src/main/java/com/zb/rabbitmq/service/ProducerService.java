package com.zb.rabbitmq.service;


import com.zb.rabbitmq.component.MyRabbitTemplate;
import com.zb.rabbitmq.config.RabbitMqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * rabbitmq消息的生产这
 * Created by Zhangbing on 2018/12/1.
 */
@Component
public class ProducerService implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired MyRabbitTemplate myRabbitTemplate;

    public void publisher(String message){
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);
        myRabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY1,message,correlationId,this);

    }

    /**
     * 消息的回调，主要是实现RabbitTemplate.ConfirmCallback接口
     * 注意，消息回调只能代表成功消息发送到RabbitMQ服务器，不能代表消息被成功处理和接受
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println(" 回调id:" + correlationData);
        if (b) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + s+"\n重新发送");

        }
    }

    /**
     * 消息失败返回 比如路由不到队列
     * @param message
     * @param i
     * @param s
     * @param s1
     * @param s2
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("未路由到队列");
    }
}
