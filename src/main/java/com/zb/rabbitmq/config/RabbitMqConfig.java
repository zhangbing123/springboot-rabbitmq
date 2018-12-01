package com.zb.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Zhangbing on 2018/12/1.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqConfig {

    private String host;//主机地址

    private String username;//用户名

    private String password;//密码

    private String virtualHost;//虚拟主机

    private int port;//端口号


    /** 消息交换机的名字*/
    public static final String EXCHANGE = "my-mq-exchange";
    /**队列1名称**/
    public static final String QUEUEONE = "queue_one";
    /** 队列key1*/
    public static final String ROUTINGKEY1 = "queue_one_key1";
    /**队列2名称**/
    public static final String QUEUETWO = "queue_two";
    /** 队列key2*/
    public static final String ROUTINGKEY2 = "queue_one_key2";


    /**
     * 配置消息交换机
     * 针对消费者配置
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }
    /**
     * 配置消息队列1
     * 针对消费者配置
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUEONE, true); //队列持久

    }
    /**
     * 将消息队列1与交换机绑定
     * 针对消费者配置
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(RabbitMqConfig.ROUTINGKEY1);
    }

    /**
     * 配置消息队列2
     * 针对消费者配置
     * @return
     */
    @Bean
    public Queue queue1() {
        return new Queue(QUEUETWO, true); //队列持久

    }
    /**
     * 将消息队列2与交换机绑定
     * 针对消费者配置
     * @return
     */
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(defaultExchange()).with(RabbitMqConfig.ROUTINGKEY2);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
