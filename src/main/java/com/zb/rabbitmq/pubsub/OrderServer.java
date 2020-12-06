package com.zb.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.instance.RabbitMQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布订阅模式-生产者
 */
public class OrderServer {

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接
        Connection connection = RabbitMQConnection.getInstance();

        //创建虚拟连接
        Channel channel = connection.createChannel();

        //发布订阅模式生产者不需要声明队列，只需要发布到某个交换机中即可，消息进入队列是有交换机路由过去的

        /**
         * 发布消息
         * 参数1：交换机，hello world模式暂时用不到
         * 参数2：消息队列，表示消息发布到哪个队列
         * 参数3：可以设置属性参数
         * 参数4：消息数据的字节数组
         */
        channel.basicPublish(Constants.PUBSUB_EXCHANGE,"",null,"订单id：1，下单成功".getBytes());

        channel.close();
        connection.close();

        System.out.println("消息发送成功");

    }
}
