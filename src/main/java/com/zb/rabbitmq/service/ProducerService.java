package com.zb.rabbitmq.service;


import com.zb.rabbitmq.constants.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.zb.rabbitmq.constants.Constants.*;


/**
 * rabbitmq消息的生产这
 * Created by Zhangbing on 2018/12/1.
 */
@Component
public class ProducerService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void publisher(String message, String routingkey) {
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);

        //设置消息发送确认
        rabbitTemplate.setConfirmCallback(this);
        //设置消息发送失败通知
        rabbitTemplate.setReturnCallback(this);

        rabbitTemplate.convertAndSend(CONFIRM_FANOUT_EXCHANGE, routingkey, message, correlationId);

    }

    /**
     * 消息进入交换机
     * 该回调方法只能确认消息是否成功到达交换机
     *
     * @param correlationData
     * @param b
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("消息成功投递到交换机");
        } else {
            System.out.println("消息投递到交换机失败:" + s);

        }
    }

    /**
     * return方法是保证消息从交换机到达队列的可靠性机制
     * 如果消息没有成功到达队列，消息会被退回给生产者
     * <p>
     * 比如没有路由到队列
     * 比如队列满了
     *
     * @param message
     * @param i
     * @param s
     * @param s1
     * @param s2
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("消息:" + new String(message.getBody()) + "未成功投递到队列，已被退回:" + s);
    }

    /**
     * 限流
     *
     * @param message
     * @param routingKey
     */
    public void limiting(String message, String routingKey) {

        //设置消息发送确认
        rabbitTemplate.setConfirmCallback(this);
        //设置消息发送失败通知
        rabbitTemplate.setReturnCallback(this);

        for (int i = 0; i < 1000; i++) {

            int finalI = i;
            new Thread(() -> {
                String uuid = UUID.randomUUID().toString();
                CorrelationData correlationId = new CorrelationData(routingKey + ":" + finalI);

                rabbitTemplate.convertAndSend(LIMIT_DIRECT_EXCHANGE, routingKey, message + ":" + finalI, correlationId);
            }).start();
        }

    }

    /**
     * 针对每个消息设置过期时间
     *
     * @param message
     * @param routingKey
     */
    public void ttlForSingleMsg(String message, String routingKey) {

        //设置消息发送确认
        rabbitTemplate.setConfirmCallback(this);
        //设置消息发送失败通知
        rabbitTemplate.setReturnCallback(this);

        MessagePostProcessor messagePostProcessor = processor -> {
            processor.getMessageProperties().setExpiration("20000");//设置存活时间
            return processor;
        };

        rabbitTemplate.convertAndSend(Constants.DELAY_EXCHANGE, Constants.DELAY_QUEUE, message, messagePostProcessor);

    }

    /**
     * 针对队列配置相同的过期时间
     *
     * @param message
     * @param routingKey
     */
    public void ttlForQueue(String message, String routingKey) {

        //设置消息发送确认
        rabbitTemplate.setConfirmCallback(this);
        //设置消息发送失败通知
        rabbitTemplate.setReturnCallback(this);

        rabbitTemplate.convertAndSend("", TTL_TEST_QUEUE2, message);

    }

    /**
     * 针对队列配置相同的过期时间
     *
     * @param message
     * @param routingKey
     */
    public void sendDelayMsg(String message, String routingKey) {

        //设置消息发送确认
        rabbitTemplate.setConfirmCallback(this);
        //设置消息发送失败通知
        rabbitTemplate.setReturnCallback(this);

        MessagePostProcessor messagePostProcessor = message1 -> {
            message1.getMessageProperties().setExpiration("5000");
            return message1;
        };

        rabbitTemplate.convertAndSend(Constants.DELAY_EXCHANGE, routingKey, message, messagePostProcessor);

    }
}
