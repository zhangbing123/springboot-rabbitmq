package com.zb.rabbitmq.instance;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQConnection {

    private static volatile ConnectionFactory connectionFactory = null;

    private RabbitMQConnection() {
    }

    public static Connection getInstance() {
        if (connectionFactory == null) {
            synchronized (RabbitMQConnection.class) {
                if (connectionFactory == null) {
                    connectionFactory = new ConnectionFactory();
                    connectionFactory.setHost("192.168.115.133");
                    connectionFactory.setPort(5672);
                    connectionFactory.setUsername("zhangbing");
                    connectionFactory.setPassword("zhangbing");
                    connectionFactory.setVirtualHost("/zb");
                }
            }
        }


        Connection connection = createConnection(0);
        if (connection != null) {
            System.out.println("连接成功...");
        }
        return connection;

    }

    private static Connection createConnection(int i) {

        try {
            //创建连接
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            if (i < 3) {
                i = i + 1;
                System.out.println("连接超时，尝试第" + i + "次重连...");
                return createConnection(i);
            }
        }
        return null;
    }
}
