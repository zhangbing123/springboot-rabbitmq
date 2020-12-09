package com.zb.rabbitmq.pattern.delayed;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.pattern.instance.RabbitMQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-09 11:08
 **/
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQConnection.getInstance();
        Channel channel = connection.createChannel();
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.expiration("20000");
        //消息过期成为死信
//        channel.basicPublish(Constants.DELAY_EXCHANGE, "delay_routing", builder.build(), "测试延时消息".getBytes());

        //消息不设置过期时间  消费者拒收也会成为死信
        channel.basicPublish(Constants.DELAY_EXCHANGE, "delay_routing", null, "测试延时消息22222".getBytes());
        System.out.println("消息发送成功，发送时间：" + System.currentTimeMillis());

        channel.close();
        connection.close();
    }
}
