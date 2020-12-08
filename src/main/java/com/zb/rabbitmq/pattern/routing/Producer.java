package com.zb.rabbitmq.pattern.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.pattern.instance.RabbitMQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: 路由模式-生产者
 * @author: zhangbing
 * @create: 2020-12-07 17:56
 **/
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitMQConnection.getInstance();

        Channel channel = connection.createChannel();

        /**
         * 交换机参数
         * routing key
         * 参数
         * 消息内容
         */
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(Constants.ROUTING_EXCHANGE, "routing_test", null, (String.valueOf(i) + ":路由模式的消息").getBytes());
        }

        channel.close();
        connection.close();

        System.out.println("消息发送成功");

    }
}
