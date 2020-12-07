package com.zb.rabbitmq.pattern.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.pattern.instance.RabbitMQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: topic 模式生产者
 * @author: zhangbing
 * @create: 2020-12-07 18:13
 **/
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQConnection.getInstance();
        Channel channel = connection.createChannel();

        channel.basicPublish(Constants.TOPIC_EXCHANGE,"topic.test.hello",null,"topic模式的消息".getBytes());

        channel.close();
        connection.close();

        System.out.println("消息发送成功");
    }
}
