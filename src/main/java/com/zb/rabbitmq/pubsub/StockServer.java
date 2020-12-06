package com.zb.rabbitmq.pubsub;

import com.rabbitmq.client.*;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.instance.RabbitMQConnection;

import java.io.IOException;

/**
 * 发布订阅模式-消费者1
 */
public class StockServer {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnection.getInstance();
        Channel channel = connection.createChannel();

        //发布订阅模式的消费者需要声明队列信息
        channel.queueDeclare(Constants.STOCK_QUEUE, false, false, false, null);

        //队列绑定交换机，参数1队列名称，参数2交换机名称，参数3routing key  发布订阅模式暂时用不到
        channel.queueBind(Constants.STOCK_QUEUE, Constants.PUBSUB_EXCHANGE, "");

        channel.basicQos(1);
        channel.basicConsume(Constants.STOCK_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("库存服务接收到消息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
