package com.zb.rabbitmq.pattern.delayed;

import com.rabbitmq.client.*;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.pattern.instance.RabbitMQConnection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-09 11:14
 **/
public class Consumer {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnection.getInstance();
//
//        /**
//         * 声明一个普通的队列(ttl 设置了消息的存活时间)
//         *
//         * ttl队列 与 死信交换机通过绑定关系
//         *
//         * ttl队列与DELAY_EXCHANGE交换机绑定
//         * 生产者发送的延迟消息  会到 delay_exchange--(delay_routing)--->delay_queue-->消息过期(dead_routing)-->dlx_exchange--(dead_routing)--->dead_queue
//         *
//         */
//        Channel ttlchannel = connection.createChannel();
//        //声明ttl队列
//        Map<String, Object> map = new HashMap<>(2);
//        map.put("x-dead-letter-exchange", Constants.DLX_EXCHANGE);//设置参数，消息成为死信后发往的交换机
//        map.put("x-dead-letter-routing-key", "dead_routing");//设置参数  消息成为死信后与交换机通过这个routingkey路由
//        ttlchannel.queueDeclare(Constants.DELAY_QUEUE, false, false, false, map);
//        //绑定交换机
//        ttlchannel.queueBind(Constants.DELAY_QUEUE, Constants.DELAY_EXCHANGE, "delay_routing");


        Channel channel = connection.createChannel();
        //声明死信队列
        channel.queueDeclare(Constants.DEAD_MESSAGE_QUEUE, false, false, false, null);
        //绑定死信交换机
        channel.queueBind(Constants.DEAD_MESSAGE_QUEUE, Constants.DLX_EXCHANGE, "dead_routing");

        channel.basicConsume(Constants.DEAD_MESSAGE_QUEUE, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收到延迟消息消息：" + new String(body) + ",接收时间：" + System.currentTimeMillis());
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
