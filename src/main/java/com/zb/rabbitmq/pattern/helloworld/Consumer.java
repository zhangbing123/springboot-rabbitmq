package com.zb.rabbitmq.pattern.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.pattern.instance.RabbitMQConnection;

import java.io.IOException;

/**
 * 简单模式如果有多个消费者  那么同一条消息只能被一个消费者消费，其他消费者只能消费其他消息
 *
 * 而且简单模式有多个消费者  就演变吃Worker模式了  工作模式
 */
public class Consumer {

    public static void main(String[] args) {

        Connection connection = null;
        try {
            //创建连接
            connection = RabbitMQConnection.getInstance();

            //创建信道,tcp连接中的虚拟连接，每个线程可以独立的建立一个独属于自己的虚拟连接，这样可以避免频繁创建tcp连接带来的开销了
            Channel channel = connection.createChannel();

            /**
             * channel声明绑定队列的名字：helloworld_queue 如果队列不存在 则会新建一个
             * 参数1：表示绑定的队列名称/ID
             * 参数2：表示消息是否持久化  false表示不持久化 rabbitmq服务停止后，消息数据丢失
             * 参数3：表示队列是否私有化，true表示只有第一次拥有它的消费者拥有后续访问权限，其他消费者无权访问，false代表所有消费者都可以访问
             * 参数4：表示是否需要自动删除队列，false表示rabbitmq服务停止后不自动删除队列，true相反
             * 其他参数：null
             */
            channel.queueDeclare(Constants.HELLOWORLD_QUEUE, false, false, false, null);

            channel.basicConsume(Constants.HELLOWORLD_QUEUE, false, new Reciver(channel));


        } catch (IOException e) {
            System.out.println("连接失败.......");
            e.printStackTrace();
        }
    }
}
