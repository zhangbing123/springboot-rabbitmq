package com.zb.rabbitmq.workqueue;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.instance.RabbitMQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列模式-生产者
 */
public class OrderServer {

    public static void main(String[] args) {
        try {
            //创建连接
            Connection connection = RabbitMQConnection.getInstance();
            //创建信道,tcp连接中的虚拟连接，每个线程可以独立的建立一个独属于自己的虚拟连接，这样可以避免频繁创建tcp连接带来的开销了
            Channel channel = connection.createChannel();
            channel.queueDeclare(Constants.HELLOWORLD_QUEUE, false, false, false, null);


            for (int i = 1; i <= 100; i++) {
                SMS sms = new SMS("乘客" + i, "13900000" + i, "您的车票已预订成功");
                String jsonSMS = new Gson().toJson(sms);
                channel.basicPublish("", Constants.WORK_QUEUE, null, jsonSMS.getBytes());
            }

            //资源关闭
            channel.close();
            connection.close();

            System.out.println("消息发送成功......");


        } catch (IOException e) {
            System.out.println("连接失败.......");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("连接超时.......");
            e.printStackTrace();
        }
    }
}
