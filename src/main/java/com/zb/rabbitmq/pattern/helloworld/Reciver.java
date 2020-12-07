package com.zb.rabbitmq.pattern.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class Reciver extends DefaultConsumer {
    public Reciver(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body);
        System.out.println("消费者接受到消息:" + message);

        System.out.println("接收到当前消息的ID:" + envelope.getDeliveryTag());

        //消息消费确认签收，false表示仅签收当前这条消息，true表示签收当前消费者所有的消息
        getChannel().basicAck(envelope.getDeliveryTag(),false);//如果不确认签收 这条消息还在队列当中 直到有消费者确认签收消息
    }
}
