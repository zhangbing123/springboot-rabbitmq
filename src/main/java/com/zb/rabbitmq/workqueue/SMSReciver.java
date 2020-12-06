package com.zb.rabbitmq.workqueue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class SMSReciver extends DefaultConsumer {

    private String consumerName;


    public SMSReciver(Channel channel, String consumerName) {
        super(channel);
        this.consumerName = consumerName;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

        String string = new String(body);

        System.out.println(consumerName + "接受到的消息：" + string);

        if (consumerName.equals("consumer1")){
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (consumerName.equals("consumer2")){
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (consumerName.equals("consumer3")){
            try {
                Thread.currentThread().sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        getChannel().basicAck(envelope.getDeliveryTag(), false);

    }
}
