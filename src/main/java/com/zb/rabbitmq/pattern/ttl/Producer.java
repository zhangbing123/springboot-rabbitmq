package com.zb.rabbitmq.pattern.ttl;

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
 * @create: 2020-12-09 10:24
 **/
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQConnection.getInstance();

        Channel channel = connection.createChannel();

        /**
         * 1.针对队列设置过期时间  队列中的所有消息过期时间一致
         */
//        //设置消息过期时间10s
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("x-message-ttl",10000);//10s
//
//        channel.queueDeclare(Constants.TTL_TEST_QUEUE,false,false,false,map);
//
//        channel.basicPublish("",Constants.TTL_TEST_QUEUE,null,"testTTl".getBytes());
//        System.out.println("消息发送成功");

        /**
         * 2.针对每条消息设置过期时间 队列中的每条消息的过期时间不同
         */
        channel.queueDeclare(Constants.TTL_TEST_QUEUE2, false, false, false, null);
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.deliveryMode(2);//延迟级别
        builder.expiration("20000");//设置过期时间

        channel.basicPublish("", Constants.TTL_TEST_QUEUE2, builder.build(), "testMessageTTl".getBytes());

        channel.close();
        connection.close();
    }
}
