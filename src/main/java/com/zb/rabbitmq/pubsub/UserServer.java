package com.zb.rabbitmq.pubsub;


import com.rabbitmq.client.*;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.instance.RabbitMQConnection;

import java.io.IOException;

/**
 * 发布订阅模式-消费者1
 */
public class UserServer {

    public static void main(String[] args) throws IOException {

        //获取连接
        Connection connection = RabbitMQConnection.getInstance();

        //创建虚拟连接 channel
        Channel channel = connection.createChannel();

        //声明队列信息
        channel.queueDeclare(Constants.USER_QUEUE, false, false, false, null);

        //队列绑定交换机 参数1队列名称，参数2交换机名称，参数3routing key  发布订阅模式暂时用不到
        channel.queueBind(Constants.USER_QUEUE, Constants.PUBSUB_EXCHANGE, "");

        channel.basicQos(1);

        channel.basicConsume(Constants.USER_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("用户服务接收到消息：" + new String(body));

                //确认签收
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });


    }
}
