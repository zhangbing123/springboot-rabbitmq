package com.zb.rabbitmq.pattern.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zb.rabbitmq.constants.Constants;
import com.zb.rabbitmq.pattern.instance.RabbitMQConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单模式-生产者
 */
public class Producer {

    public static void main(String[] args) {

        try {
            //创建连接
            Connection connection = RabbitMQConnection.getInstance();

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

            /**
             * 发布消息
             * 参数1：交换机，hello world模式暂时用不到
             * 参数2：消息队列，表示消息发布到哪个队列
             * 参数3：可以设置属性参数
             * 参数4：消息数据的字节数组
             */
            for (int i=0;i<5;i++){
                channel.basicPublish("", Constants.HELLOWORLD_QUEUE, null, (String.valueOf(i)+"hello world rabbitmq").getBytes());
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
